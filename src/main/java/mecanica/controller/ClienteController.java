package mecanica.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.ClienteDAO;
import mecanica.model.Cliente;
import mecanica.utils.ObterData;

/**
 *
 * @author Gustavo Santos
 */
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // Verificar ação para deletar cliente
        if(action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("IdCliente"));
            clienteDAO.deletar(id); 
        }
        
        List<Cliente> listaClientes = clienteDAO.listar();
        
        request.setAttribute("listaClientes", listaClientes);
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/clientes.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Date date = new Date();
        
        //date = ObterData.ObterDataHora();
        Cliente cliente = new Cliente();

        if(date != null) {
            cliente.setNome(request.getParameter("nome"));
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setTelefone(Long.parseLong(request.getParameter("telefone")));
            cliente.setDataCadastro(new java.sql.Date(date.getTime()));
            
        }
        
        try {
            clienteDAO.criar(cliente);
        } 
        catch (Exception e) {
        }
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(Integer.parseInt(request.getParameter(("IdCliente"))));
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
         
        try {
            cliente.setDataCadastro(new java.sql.Date(formatador.parse(request.getParameter("dataCadastro")).getTime()));
            clienteDAO.atualizar(cliente);
        } 
        catch (Exception e) {
        }
    }
    
    

}
