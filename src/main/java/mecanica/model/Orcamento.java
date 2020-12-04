package mecanica.model;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import mecanica.enumeration.StatusOrcamento;

/**
 *
 * @author Gustavo Santos
 * @author Victor Vilela
 */
@Getter
@Setter
public class Orcamento {

    private int idOrcamento;
    private Date dataOrcamento;
    private double totalPecas;
    private double totalMaoObra;
    private StatusOrcamento status;
    private int idCliente;
    private int idFuncionario;

    public Orcamento() {

    }

    public Orcamento(int idOrcamento, Date dataOrcamento, double totalPecas, double totalMaoObra, StatusOrcamento status, int idCliente, int idFuncionario) {
        this.idOrcamento = idOrcamento;
        this.dataOrcamento = dataOrcamento;
        this.totalPecas = totalPecas;
        this.totalMaoObra = totalMaoObra;
        this.status = status;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
    }
}
