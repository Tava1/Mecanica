package mecanica.model;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import mecanica.enumeration.StatusOrcamento;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public class RelatorioOrcamento {
    private int idOrcamento;
    private Date dataOrcamento;
    private StatusOrcamento status;
    private String nomeCliente;
    private String cpfCliente;
    private String nomeFuncionario;
    
}
