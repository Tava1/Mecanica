package mecanica.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gustavo Santos
 */
public class ConexaoDados {
    static String STATUS = "Não foi possível se conectar ao banco de dados.";
    static String DRIVER = "com.mysql.cj.jdbc.Driver";                   
    static String SERVER = "localhost";
    static String DATABASE = "mecanica";              
    static String LOGIN = "root";                           
    static String SENHA = "me1234";                         
    static String URL = "";
    static Connection CONEXAO;
    
    public static Connection abrirConexao() throws ClassNotFoundException, SQLException {
        
        URL = "jdbc:mysql://" + SERVER + ":3306/" + DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
        
        if (CONEXAO == null) {
            try {
                Class.forName(DRIVER);
                CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
                
                if (CONEXAO != null) {
                    STATUS = "A Conexão com o banco de dados foi estabelecida.";
                }
                else {
                    STATUS = "Não foi possível se conectar ao banco de dados.";
                }
            } 
            catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("O driver expecificado não foi encontrado.");
            }
            catch (SQLException e) {  
                throw new SQLException("Erro ao estabelecer uma conexão com o banco de dados. Verifique se credenciais de acesso estão corretas.");
            }
        }
        else {
            try {
                if (CONEXAO.isClosed())
                    CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
            }
            catch(SQLException e) {
                throw new SQLException("Não foi possível obter uma conexão com o banco de dados.");
            }
        } 
        
        return CONEXAO;
    }
    
    public static boolean fecharConexao() throws SQLException {
        try {
            if (CONEXAO != null) {
                if(!CONEXAO.isClosed()) {
                    CONEXAO.close();
                    STATUS = "A Conexão com o banco de dados foi finalizada.";
                }
            }
        }
        catch (SQLException e) {
            return false;
        }
        
        return true;
    }
    
    public static String obterStatusConexao() {
        return STATUS;
    }
}
