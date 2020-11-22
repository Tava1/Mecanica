/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanica.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.FuncionarioDAO;
import mecanica.model.Funcionario;

/**
 *
 * @author Felipe Dias
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController"})
public class FuncionarioController extends HttpServlet {
     
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // Verificar ação para deletar funcionario
        if(action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("idFuncionario"));
            funcionarioDAO.deletar(id); 
        }
        
        List<Funcionario> listaFuncionario = funcionarioDAO.listar();
        
        request.setAttribute("listaFuncionarios", listaFuncionario);
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/funcionarios.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Funcionario funcionario = new Funcionario();

        funcionario.setCargo(request.getParameter("cargo"));
        
        try {
            funcionarioDAO.criar(funcionario);
        } 
        catch (Exception e) {
        }
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setIdFuncionario(Integer.parseInt(request.getParameter(("IdFuncionario"))));
     
        try {        
            funcionarioDAO.atualizar(funcionario);
        } 
        catch (Exception e) {
        }
    }
    
}
