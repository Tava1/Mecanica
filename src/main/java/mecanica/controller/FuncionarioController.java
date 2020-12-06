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
import mecanica.dao.FuncionarioDAO;
import mecanica.model.Funcionario;
import mecanica.utils.LerJson;
import org.json.JSONObject;

/**
 * @author Felipe Dias
 * @author Gustavo Santos
 */
@WebServlet(name = "FuncionarioController", urlPatterns = "/FuncionarioController")
public class FuncionarioController extends HttpServlet {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Funcionario> listaFuncionarios = this.funcionarioDAO.listar();
        
        String jsonString = this.gson.toJson(listaFuncionarios);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject jsonFuncionario = LerJson.lerJsonObj(request);
        
        Funcionario funcionario = new Funcionario();
        Date date = new Date();

        funcionario.setNome(jsonFuncionario.getString("nome"));
        funcionario.setCpf(jsonFuncionario.getString("cpf"));
        funcionario.setTelefone(Long.parseLong(jsonFuncionario.getString("telefone")));
        funcionario.setCargo(jsonFuncionario.getString("cargo"));
        
        String retorno = "";

        try {
            retorno = funcionarioDAO.criar(funcionario);
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

        JSONObject jsonFuncionario = LerJson.lerJsonObj(request);
        Funcionario funcionario = new Funcionario();
        String retorno = "";

        try {

            funcionario.setIdFuncionario(jsonFuncionario.getInt("id"));
            funcionario.setNome(jsonFuncionario.getString("nome"));
            funcionario.setCpf(jsonFuncionario.getString("cpf"));
            funcionario.setTelefone(Long.parseLong(jsonFuncionario.getString("telefone")));
            funcionario.setCargo(jsonFuncionario.getString("cargo"));
        
            retorno = funcionarioDAO.atualizar(funcionario);
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
            int id = Integer.parseInt(request.getParameter("idFuncionario"));
            retorno = funcionarioDAO.deletar(id);
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
