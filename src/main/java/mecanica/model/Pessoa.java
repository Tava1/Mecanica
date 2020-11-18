package mecanica.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public abstract class Pessoa {
    private String nome;
    private String cpf;
    private long telefone;
}
