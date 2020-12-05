package mecanica.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.OrcamentoDAO;
import mecanica.model.Orcamento;

/**
 *
 * @author Victor Vilela
 */
public class OrcamentoController extends HttpServlet {

    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Verificar ação para deletar orcamento
        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("idOrcamento"));
            orcamentoDAO.deletar(id);
        }

        List<Orcamento> listaOrcamento = orcamentoDAO.listar();

        request.setAttribute("listaOrcamento", listaOrcamento);
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/orcamentos.jsp");
        view.forward(request, response);
    }
    
}