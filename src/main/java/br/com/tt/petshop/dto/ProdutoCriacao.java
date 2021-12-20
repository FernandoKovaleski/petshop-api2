package br.com.tt.petshop.dto;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

public class ProdutoCriacao {
    private final String nome;
    private final BigDecimal valor;
    private final boolean ativo;

    @ConstructorProperties({"nome", "valor", "ativo"})
    public ProdutoCriacao(String nome, BigDecimal valor, boolean ativo) {
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
