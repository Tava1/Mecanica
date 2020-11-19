package mecanica.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Santos
 */
public interface IInteracaoDAO<T> {
    
    public boolean criar(T t);
    public ArrayList<T> listar();
    public boolean atualizar(T t);
    public boolean deletar(int id);
}
