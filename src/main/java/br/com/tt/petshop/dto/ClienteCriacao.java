package br.com.tt.petshop.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class ClienteCriacao {

    //@NotNull(message = "O nome é obrigatório")
    //@NotEmpty(message = "O nome não pode ser vazio!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private final String nome;

    @NotBlank(message = "O nome não pode ser vazio!")
    @CPF(message = "Informe um CPF válido!")
    private final String cpf;

    @NotBlank(message = "O telefone não pode ser vazio!")
    private final String telefone;

    @NotNull(message = "Nascimento obrigatório!")
    private final LocalDate nascimento;

    @ConstructorProperties({"nome", "cpf", "telefone", "nascimento"})
    public ClienteCriacao(String nome, String cpf, String telefone, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
}
