package mecanica.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public class Funcionario extends Pessoa{
    private int idFuncionario;
    private String cargo;
    
    public Funcionario(int idFuncionario, String cargo) {
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
    }
    
    @Override
    public String toString(){
        return String.format("idFuncionario %d <br/> cargo %s <br/> ", this.getIdFuncionario(),this.getCargo());
    }
}
