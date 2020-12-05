package mecanica.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gustavo Santos
 */

@Getter
@Setter
public class Funcionario extends Pessoa{
    private int idFuncionario;
    private String nome;
    private String cpf;
    private long telefone;
    private String cargo;

    public Funcionario() {
    }
    
    public Funcionario(int idFuncionario, String nome, String cpf, long telefone, String cargo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }
}
