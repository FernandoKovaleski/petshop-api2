package br.com.tt.petshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@Schema(description = "Objeto enviado para atualização de todos os campos do Produto") //ApiModel
public class ProdutoAtualizacao {

    private final String nome;
    @Schema(description = "Valor em reais. Exemplo: 12.5 ")
    private final BigDecimal valor;
    private final boolean ativo;

    @ConstructorProperties({"nome", "valor", "ativo"})
    public ProdutoAtualizacao(String nome, BigDecimal valor, boolean ativo) {
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
