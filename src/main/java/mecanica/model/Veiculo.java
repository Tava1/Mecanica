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

    public Veiculo(int idVeiculo, String modelo, String marca, int ano, TipoVeiculo tipoVeiculo, int idCliente) {
        this.idVeiculo = idVeiculo;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.tipoVeiculo = tipoVeiculo;
        this.idCliente = idCliente;
    }
}

/*Chamada para enum
    TipoVeiculo suv = TipoVeiculo.SUV;*/