package mecanica.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.ClienteDAO;
import mecanica.model.Cliente;
import mecanica.utils.LerJson;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gustavo Santos
 */

@WebServlet(name = "ClienteController", urlPatterns = "/ClienteController")
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> listaClientes = this.clienteDAO.listar();
        
        String jsonString = this.gson.toJson(listaClientes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject jsonCliente = LerJson.lerJsonObj(request);
        
        Cliente cliente = new Cliente();
        Date date = new Date();

        // Preencher o objeto cliente lendo a respectiva propriedade do JSON
        cliente.setNome(jsonCliente.getString("nome"));
        cliente.setCpf(jsonCliente.getString("cpf"));
        cliente.setTelefone(Long.parseLong(jsonCliente.getString("telefone")));
        cliente.setDataCadastro(new java.sql.Date(date.getTime()));
        
        String retorno = "";

        try {
            retorno = clienteDAO.criar(cliente);
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

        JSONObject jsonCliente = LerJson.lerJsonObj(request);
        Cliente cliente = new Cliente();
        String retorno = "";

        try {

            // Preencher o objeto cliente lendo a respectiva propriedade do JSON
            cliente.setIdCliente(jsonCliente.getInt("id"));
            cliente.setNome(jsonCliente.getString("nome"));
            cliente.setCpf(jsonCliente.getString("cpf"));
            cliente.setTelefone(Long.parseLong(jsonCliente.getString("telefone")));
        
            retorno = clienteDAO.atualizar(cliente);
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
            int id = Integer.parseInt(request.getParameter("IdCliente"));
            retorno = clienteDAO.deletar(id);
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
