package mecanica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mecanica.model.Funcionario;
import mecanica.utils.ConexaoDados;
import mecanica.interfaces.IInteracaoDAO;

/**
 *
 * @author Gustavo Santos
 */

public class FuncionarioDAO implements IInteracaoDAO<Funcionario>{
    
    @Override
    public boolean criar(Funcionario funcionario) {
        PreparedStatement ps = null;
        
        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("INSERT INTO Funcionario(Nome, CPF, Telefone, Cargo) VALUES (?, ?, ?, ?);");
            
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setLong(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getCargo());
            
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
    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("SELECT * FROM Funcionario;");
            
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setIdFuncionario(resultSet.getInt("IdFuncionario"));
                funcionario.setNome(resultSet.getString("Nome"));
                funcionario.setCpf(resultSet.getString("CPF"));
                funcionario.setTelefone(resultSet.getLong("Telefone"));
                funcionario.setCargo(resultSet.getString("Cargo"));
                
                funcionarios.add(funcionario);
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
        
        return funcionarios;
    }
    
    @Override
    public boolean atualizar(Funcionario funcionario) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("UPDATE Funcionario SET Nome = ?, CPF = ?, Telefone = ?, Cargo = ? WHERE IdFuncionario = ?;");
            
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setLong(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getCargo());

            // WHERE
            ps.setInt(5, funcionario.getIdFuncionario());
            
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
            ps = conn.prepareStatement("DELETE FROM Funcionario WHERE FuncionarioId = ?;");
            
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
