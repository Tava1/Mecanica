/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanica.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mecanica.dao.VeiculoDAO;
import mecanica.model.Veiculo;

/**
 *
 * @author JHK
 */
public class VeiculoController extends HttpServlet {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        //verificando ação para deletar veiculo
        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("IdVeiculo"));
            veiculoDAO.deletar(id);
        }

        List<Veiculo> listaVeiculos = veiculoDAO.listar();

        request.setAttribute("listaVeiculos", listaVeiculos);
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = getServletContext().getRequestDispatcher("/veiculos.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Veiculo veiculo = new Veiculo();

        veiculo.setModelo(request.getParameter("modelo"));
        veiculo.setMarca(request.getParameter("marca"));
        veiculo.setAno(Integer.parseInt(request.getParameter("ano")));
        //veiculo.setTipoVeiculo(request.getParameterNames());
        veiculo.setIdCliente(Integer.parseInt(request.getParameter("IdCliente")));

        try {
            veiculoDAO.criar(veiculo);
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Veiculo veiculo = new Veiculo();
        veiculo.setIdVeiculo(Integer.parseInt(request.getParameter("IdVeiculo")));

        try {
            veiculoDAO.atualizar(veiculo);
        } catch (Exception e) {
        }
    }
}
