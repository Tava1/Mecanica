package mecanica.model;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public class Cliente extends Pessoa {
    private int IdCliente;
    private Date dataCadastro;
}
