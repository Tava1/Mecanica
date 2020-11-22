package mecanica.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 * @author Victor Vilela
 */

@Getter
@Setter
public class DetalheOrcamento {
    private int idDetalheOrcamento;
    private String descricao;
    private int quantidade;
    private double precoUnitario;
    private int idOrcamento;
    
    
    public DetalheOrcamento() {

    }

    public DetalheOrcamento(int idDetalheOrcamento, String descricao, int quantidade, double precoUnitario, int idOrcamento) {
        this.idDetalheOrcamento = idDetalheOrcamento;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.idOrcamento = idOrcamento;
    }
}
