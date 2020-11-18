package mecanica.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public class Orcamento {
    private int idOrcamento;
    private Date dataOrcamento;
    private double totalPecas;
    private double totalMaoObra;
    private int statusOrcamento;
}
