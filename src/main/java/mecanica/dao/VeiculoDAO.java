package mecanica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mecanica.interfaces.IInteracaoDAO;
import mecanica.model.Veiculo;
import mecanica.utils.ConexaoDados;
import mecanica.enumeration.TipoVeiculo;

/**
 *
 * @author Gustavo Santos
 */
public class VeiculoDAO implements IInteracaoDAO<Veiculo>{

    @Override
    public String criar(Veiculo veiculo) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("INSERT INTO Veiculo(Modelo, Marca, Ano) VALUES (?, ?, ?);");

            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getMarca());
            ps.setLong(3, veiculo.getAno());

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

        return "Veículo cadastrado com sucesso.";
    }

    @Override
    public ArrayList<Veiculo> listar() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("SELECT * FROM Veiculo;");

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Veiculo veiculo = new Veiculo();

                veiculo.setIdVeiculo(resultSet.getInt("IdVeiculo"));
                veiculo.setModelo(resultSet.getString("Modelo"));
                veiculo.setMarca(resultSet.getString("Marca"));
                veiculo.setAno(resultSet.getInt("Ano"));

                veiculos.add(veiculo);
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

        return veiculos;
    }
    
    @Override
    public String atualizar(Veiculo veiculo) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("UPDATE Veiculo SET Modelo = ?, Marca = ?, Ano = ?, WHERE IdVeiculo = ?;");

            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getMarca());
            ps.setLong(3, veiculo.getAno());
            // WHERE
            ps.setInt(4, veiculo.getIdCliente());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return "Este veículo não pode ser alterado.";
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

        return "Veículo atualizado com sucesso.";
    }

    @Override
    public String deletar(int id) {

        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("DELETE FROM Veiculo WHERE IdVeiculo = ?;");

            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return "Este Veiculo não pode ser deletado";
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
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "Veiculo deletado com sucesso.";
    }
}
