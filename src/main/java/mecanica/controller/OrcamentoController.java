package mecanica.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.ClienteDAO;
import mecanica.dao.FuncionarioDAO;
import mecanica.dao.OrcamentoDAO;
import mecanica.model.Cliente;
import mecanica.model.DetalheOrcamento;
import mecanica.model.Funcionario;
import mecanica.model.Orcamento;
import mecanica.utils.LerJson;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Victor Vilela
 */
public class OrcamentoController extends HttpServlet {

    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Orcamento> listaOrcamentos = this.orcamentoDAO.listar();
        
        String jsonString = this.gson.toJson(listaOrcamentos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject jsonOrcamento = LerJson.lerJsonObj(request);
        //JSONArray jsonDetalheOrcamento = jsonOrcamento.getJSONArray("detalhe");
        
        Date date = new Date();
        String retorno = "";
        
        try {
            String cpfCliente = jsonOrcamento.getString("cpfCliente");
            String cpfFuncionario = jsonOrcamento.getString("cpfFuncionario");

            // Buscar cliente pelo CPF
            Cliente cliente = clienteDAO.buscarCPF(cpfCliente);
            Funcionario funcionario = funcionarioDAO.buscarCPF(cpfFuncionario);

            if (cliente != null && funcionario != null) {
                Orcamento orcamento = new Orcamento();
                
                orcamento.setIdCliente(cliente.getIdCliente());
                orcamento.setIdFuncionario(funcionario.getIdFuncionario());
                orcamento.setTotalMaoObra(jsonOrcamento.getDouble("totalMaoObra"));
                orcamento.setDataOrcamento(new java.sql.Date(date.getTime()));
                
                ArrayList<DetalheOrcamento> listaDetalheOrcamento = new ArrayList<>();
                
                // Obtem por meio do JSON o detalhamento dos itens cadastrado no orçamento 
//                for (int i = 0; i < jsonDetalheOrcamento.length(); i++) {
//                    DetalheOrcamento detalheOrcamento = new DetalheOrcamento();
//                    String descricao = jsonDetalheOrcamento.getJSONObject(i).getString("descricao");
//                    int quantidade = jsonDetalheOrcamento.getJSONObject(i).getInt("quantidade");
//                    double precoUnitario = jsonDetalheOrcamento.getJSONObject(i).getDouble("precoUnitario");
//
//                    detalheOrcamento.setDescricao(descricao);
//                    detalheOrcamento.setQuantidade(quantidade);
//                    detalheOrcamento.setPrecoUnitario(precoUnitario);
//                    
//                    listaDetalheOrcamento.add(detalheOrcamento);
//                }
                DetalheOrcamento detalheOrcamento = new DetalheOrcamento();
                String descricao = jsonOrcamento.getString("descricao");
                int quantidade = Integer.parseInt(jsonOrcamento.getString("quantidade"));
                double precoUnitario = Double.parseDouble(jsonOrcamento.getString("precoUnitario"));

                detalheOrcamento.setDescricao(descricao);
                detalheOrcamento.setQuantidade(quantidade);
                detalheOrcamento.setPrecoUnitario(precoUnitario);

                listaDetalheOrcamento.add(detalheOrcamento);

                retorno = orcamentoDAO.criar(orcamento, listaDetalheOrcamento);
            }
            else {
                retorno = "CPF Incorreto ou cliente não possuí cadastro no sistema";
            }
        } 
        catch (Exception e) {
            retorno = e.getMessage();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonString = this.gson.toJson(retorno);
        response.getWriter().write(jsonString);
    }
    
    
    
    
}