package br.com.tt.petshop.dto;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class ClienteAtualizacao {

    private final String nome;
    private final String telefone;
    private final LocalDate nascimento;

    /*
    Essa anotação resolve o erro de:
        Type definition error: [simple type, class br.com.tt.petshop.dto.ClienteAtualizacao]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `br.com.tt.petshop.dto.ClienteAtualizacao` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)\n at [Source: (PushbackInputStream); line: 2, column: 5]

     */
    @ConstructorProperties({"nome", "telefone", "nascimento"})
    public ClienteAtualizacao(String nome, String telefone, LocalDate nascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
}
