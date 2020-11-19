package mecanica.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public class DetalheOrcamento {
    private int idDetalheOrcamento;
    private String descricao;
    private int quantidade;
    private double precoUnitario;
    private int idOrcamento;
}
