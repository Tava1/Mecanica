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
public class Orcamento {
    private int idOrcamento;
    private Date dateOrcamento;
    private double totalPecas;
    private double totalMaoObra;
    private StatusOrcamento status;
    private int idClinte;
    private int idFuncionario;
    
}
