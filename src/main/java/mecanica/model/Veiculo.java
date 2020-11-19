package mecanica.model;

import lombok.Getter;
import lombok.Setter;
import mecanica.enumeration.TipoVeiculo;

/**
 *
 * @author Gustavo Santos
 */
@Getter
@Setter
public class Veiculo {

    private int idVeiculo;
    private String modelo;
    private String marca;
    private int ano;
    private TipoVeiculo tipoVeiculo;
    private int idCliente;
    
    public Veiculo() {
        
    }
    
    public Veiculo (int idVeiculo, String modelo, String marca, int ano, TipoVeiculo tipoVeiculo, int idCliente) {
        
    }
    
    @Override
    public String toString() {
        return String.format("idVeiculo %s <br/> "
                + "modelo %s <br/> "
                + "marca %s <br/> "
                + "ano %s <br/> "
                + "tipoVeiculo %s <br/> "
                + "idCliente %s <br/> ",
                this.getIdVeiculo(),
                this.getModelo(),
                this.getMarca(),
                this.getAno(),
                this.getTipoVeiculo(),
                this.getIdCliente());
    }
}

/*Chamada para enum
    TipoVeiculo suv = TipoVeiculo.SUV;*/