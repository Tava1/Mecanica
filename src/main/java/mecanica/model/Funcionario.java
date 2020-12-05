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
}
