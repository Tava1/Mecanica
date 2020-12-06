/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mecanica.enumeration.StatusOrcamento;
import mecanica.interfaces.IInteracaoDAO;
import mecanica.model.DetalheOrcamento;
import mecanica.model.Orcamento;
import mecanica.utils.ConexaoDados;

/**
 *
 * @author Victor Vilela
 */
public class OrcamentoDAO implements IInteracaoDAO<Orcamento>{

    @Override
    public String criar(Orcamento orcamento) {        
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("INSERT INTO Orcamento(idOrcamento, dataOrcamento, totalPecas, totalMaoObra, statusOrcamento, idCliente, idFuncionario) VALUES (?, ?, ?, ?, ?, ?, ?);");

            ps.setInt(1, orcamento.getIdOrcamento());
            ps.setDate(2, orcamento.getDataOrcamento());
            ps.setDouble(3, orcamento.getTotalPecas());
            ps.setDouble(4, orcamento.getTotalMaoObra());
//            ps.setString(5, orcamento.getStatus());
            ps.setInt(6, orcamento.getIdCliente());
            ps.setInt(7, orcamento.getIdFuncionario());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return "Não foi possível cadastrar um novo veículo.";
            }

        } 
        catch (Exception e) {
            return e.getMessage();
        } 
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
                return e.getMessage();
            }
        }

        return "Orçamento cadastrado com sucesso.";
    }

    public String criar(Orcamento orcamento, ArrayList<DetalheOrcamento> listaDetalhe) {        
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("INSERT INTO Orcamento(idOrcamento, dataOrcamento, totalPecas, totalMaoObra, statusOrcamento, idCliente, idFuncionario) VALUES (?, ?, ?, ?, ?, ?, ?);");

            ps.setInt(1, orcamento.getIdOrcamento());
            ps.setDate(2, orcamento.getDataOrcamento());
            ps.setDouble(3, orcamento.getTotalPecas());
            ps.setDouble(4, orcamento.getTotalMaoObra());
//            ps.setString(5, orcamento.getStatus());
            ps.setInt(6, orcamento.getIdCliente());
            ps.setInt(7, orcamento.getIdFuncionario());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return "Não foi possível cadastrar um novo veículo.";
            }

        } 
        catch (Exception e) {
            return e.getMessage();
        } 
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
                return e.getMessage();
            }
        }

        return "Orçamento cadastrado com sucesso.";
    }
    
    @Override
    public ArrayList<Orcamento> listar() {
        ArrayList<Orcamento> listaOrc = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("SELECT * FROM Orcamento;");

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Orcamento orcamento = new Orcamento();
                
                orcamento.setIdOrcamento(resultSet.getInt("IdOrcamento"));
                orcamento.setDataOrcamento(resultSet.getDate("DataOrcamento"));
                orcamento.setTotalPecas(resultSet.getDouble("TotalPecas"));
                orcamento.setTotalMaoObra(resultSet.getDouble("TotalMaoObra"));
                orcamento.setStatus(StatusOrcamento.Iniciado);
                orcamento.setIdCliente(resultSet.getInt("IdCliente"));
                orcamento.setIdFuncionario(resultSet.getInt("IdFuncionario"));

                listaOrc.add(orcamento);
            }
        } 
        catch (Exception e) {
            return null;
        } 
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
                return null;
            }
        }
        return listaOrc;
    }

    @Override
    public String atualizar(Orcamento orcamento) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("UPDATE Orcamento SET DataOrcamento = ?, TotalPecas = ?, TotalMaoObra = ?, Status = ?, WHERE IdCliente = ?;");
            
            ps.setDate(1, orcamento.getDataOrcamento());
            ps.setDouble(2, orcamento.getTotalPecas());
            ps.setDouble(3, orcamento.getTotalMaoObra());
//            ps.setInt(1, orcamento.getStatus());
            // WHERE
            ps.setInt(5, orcamento.getIdCliente());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return "Este orçamento não pode ser alterado.";
            }

        } 
        catch (Exception e) {
            return e.getMessage();
        } 
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
                return e.getMessage();
            }
        }
        return "Orçamento atualizado com sucesso.";
    }

    @Override
    public String deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
