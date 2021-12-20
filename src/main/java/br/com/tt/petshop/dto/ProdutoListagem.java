package br.com.tt.petshop.dto;

import java.math.BigDecimal;

public class ProdutoListagem {
    private final Long id;
    private final String nome;
    private final BigDecimal valor;

    public ProdutoListagem(Long id, String nome, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
