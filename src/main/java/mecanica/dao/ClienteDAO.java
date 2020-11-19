package mecanica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mecanica.interfaces.IInteracaoDAO;
import mecanica.model.Cliente;
import mecanica.utils.ConexaoDados;

/**
 *
 * @author Gustavo Santos
 */
public class ClienteDAO implements IInteracaoDAO<Cliente>{
    
    @Override
    public boolean criar(Cliente cliente) {
        PreparedStatement ps = null;
        
        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("INSERT INTO Cliente(Nome, CPF, Telefone, DataCadastro) VALUES (?, ?, ?, ?);");
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setLong(3, cliente.getTelefone());
            ps.setDate(4, cliente.getDataCadastro());
            
            int linhasAfetadas = ps.executeUpdate();
            
            if (linhasAfetadas <= 0) {
                return false;
            }
        
        } 
        catch (Exception e) {
            return false;
        }
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
            }
        }
        
        return true;
    }
    
    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("SELECT * FROM Cliente;");
            
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setIdCliente(resultSet.getInt("IdCliente"));
                cliente.setNome(resultSet.getString("Nome"));
                cliente.setCpf(resultSet.getString("CPF"));
                cliente.setTelefone(resultSet.getLong("Telefone"));
                cliente.setDataCadastro(resultSet.getDate("DataCadastro"));
                
                clientes.add(cliente);
            }
        } 
        catch (Exception e) {
        }
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
            }
        }
        
        return clientes;
    }
    
    @Override
    public boolean atualizar(Cliente cliente) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("UPDATE Cliente SET Nome = ?, CPF = ?, Telefone = ?, DataCadastro = ? WHERE IdCliente = ?;");
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setLong(3, cliente.getTelefone());
            ps.setDate(4, cliente.getDataCadastro());
            // WHERE
            ps.setInt(13, cliente.getIdCliente());
            
            int linhasAfetadas = ps.executeUpdate();
            
            if (linhasAfetadas <= 0) {
                return false;
            }
        
        } 
        catch (Exception e) {
            return false;
        }
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
            }
        }
        
        return true;
    }
    
    @Override
    public boolean deletar(int id) {

        PreparedStatement ps = null;
        
        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("DELETE FROM Cliente WHERE IdCliente = ?;");
            
            ps.setInt(1, id);
            
            int linhasAfetadas = ps.executeUpdate();
            
            if (linhasAfetadas <= 0) {
                return false;
            }
        
        } 
        catch (Exception e) {
            return false;
        }
        finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } 
            catch (Exception e) {
            }
        }

        return true;
    }
    
}
