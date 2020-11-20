package mecanica.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Santos
 */
public interface IInteracaoDAO<T> {
    
    public String criar(T t);
    public ArrayList<T> listar();
    public String atualizar(T t);
    public String deletar(int id);
}
