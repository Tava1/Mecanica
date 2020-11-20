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
public class VeiculoDAO {

    public boolean criar(Veiculo veiculo) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("INSERT INTO Veiculo(Modelo, Marca, Ano) VALUES (?, ?, ?);");

            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getMarca());
            ps.setLong(3, veiculo.getAno());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return false;
            }

        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } catch (Exception e) {
            }
        }

        return true;
    }

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
        } catch (Exception e) {
        } finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } catch (Exception e) {
            }
        }

        return veiculos;
    }

    public boolean atualizar(Veiculo veiculo) {
        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("UPDATE Veiculo SET Modeo = ?, Marca = ?, Ano = ?, WHERE IdVeiculo = ?;");

            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getMarca());
            ps.setLong(3, veiculo.getAno());
            // WHERE
            ps.setInt(4, veiculo.getIdCliente());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return false;
            }

        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } catch (Exception e) {
            }
        }

        return true;
    }

    public boolean deletar(int id) {

        PreparedStatement ps = null;

        try {
            Connection conn = ConexaoDados.abrirConexao();
            ps = conn.prepareStatement("DELETE FROM Cliente WHERE IdVeiculo = ?;");

            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas <= 0) {
                return false;
            }

        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (ps != null) {
                    ConexaoDados.fecharConexao();
                }
            } catch (Exception e) {
            }
        }
        return true;
    }
}
