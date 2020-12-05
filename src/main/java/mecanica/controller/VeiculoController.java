package mecanica.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.ClienteDAO;
import mecanica.dao.VeiculoDAO;
import mecanica.model.Veiculo;
import mecanica.model.Cliente;
import org.json.JSONObject;

/**
 * @author Gustavo Santos
 * @author JHK
 */
@WebServlet(name = "VeiculoController", urlPatterns = "/VeiculoController")
public class VeiculoController extends HttpServlet {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Veiculo> listaVeiculos = this.veiculoDAO.listar();
        
        String jsonString = this.gson.toJson(listaVeiculos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject jsonVeiculo = lerJsonObj(request);
        
        Veiculo veiculo = new Veiculo();
        Date date = new Date();
        String retorno = "";
        
        try {
            String cpf = jsonVeiculo.getString("cpfCliente");

            // Buscar cliente pelo CPF
            Cliente cliente = clienteDAO.buscarCPF(cpf);

            if (cliente != null) {
                veiculo.setMarca(jsonVeiculo.getString("marca"));
                veiculo.setModelo(jsonVeiculo.getString("modelo"));
                veiculo.setAno(Integer.parseInt(jsonVeiculo.getString("ano")));
                //veiculo.setTipoVeiculo(jsonFuncionario.getString("tipoVeiculo"));
                veiculo.setIdCliente(cliente.getIdCliente());

                retorno = veiculoDAO.criar(veiculo);
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
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject jsonVeiculo = lerJsonObj(request);
        Veiculo veiculo = new Veiculo();
        String retorno = "";

        try {

            veiculo.setIdVeiculo(jsonVeiculo.getInt("id"));
            veiculo.setMarca(jsonVeiculo.getString("marca"));
            veiculo.setModelo(jsonVeiculo.getString("modelo"));
            veiculo.setAno(jsonVeiculo.getInt("ano"));
            //veiculo.setTipoVeiculo(jsonVeiculo.getString("cargo"));
            veiculo.setIdCliente(jsonVeiculo.getInt("idCliente"));

            retorno = veiculoDAO.atualizar(veiculo);
        } 
        catch (Exception e) {
            retorno = e.getMessage();
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonString = this.gson.toJson(retorno);
        response.getWriter().write(jsonString);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String retorno = "";

        try {
            int id = Integer.parseInt(request.getParameter("idVeiculo"));
            retorno = veiculoDAO.deletar(id);
        } 
        catch (Exception e) {
            retorno = e.getMessage();
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonString = this.gson.toJson(retorno);
        response.getWriter().write(jsonString);
    }

    public static JSONObject lerJsonObj(HttpServletRequest request) {
        try {
            // Ler o JSON
            StringBuilder sb = new StringBuilder();
            String line = null;

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());
            return json;
        } 
        catch (Exception e) {
            return null;
        }
    }
}

