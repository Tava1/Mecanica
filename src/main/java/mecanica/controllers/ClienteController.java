/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanica.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.ClienteDAO;
import mecanica.model.Cliente;

/**
 *
 * @author Felipe Dias
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {
    ClienteDAO DAO = new ClienteDAO();
    
  // Listar Clientes Cadastrados
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // Verificar ação para deletar cliente
        if(action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("IdCliente"));
            DAO.deletar(id); 
        }
        
        List<Cliente> listaClientes = DAO.listar();
        
        request.setAttribute("listaClientes", listaClientes);
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/clientes.jsp");
        view.forward(request, response);
    }

    // Criar Cliente
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cliente cliente = new Cliente();
        
        // Data
        Date dataAtual = new Date();

        cliente.setCpf(request.getParameter("cpf"));
        cliente.setDataCadastro( new java.sql.Date(dataAtual.getTime()));
        
        try {
            DAO.criar(cliente);
        } 
        catch (Exception e) {
        }
    }
    
    // Atualizar Cliente
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(Integer.parseInt(request.getParameter(("IdCliente"))));
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
         
        try {
            cliente.setDataCadastro(new java.sql.Date(formatador.parse(request.getParameter("dataCadastro")).getTime()));
            DAO.atualizar(cliente);
        } 
        catch (Exception e) {
        }
    }

}
