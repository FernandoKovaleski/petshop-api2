package br.com.tt.petshop.dto;

import java.time.LocalDate;

public class ClienteDetalhes {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final String telefone;
    private final LocalDate nascimento;

    public ClienteDetalhes(Long id, String nome, String cpf, String telefone, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nascimento = nascimento;
    }

    public Long getId() {
        return id;
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
