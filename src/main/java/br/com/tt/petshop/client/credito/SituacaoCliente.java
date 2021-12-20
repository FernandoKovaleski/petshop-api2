package br.com.tt.petshop.client.credito;

import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class SituacaoCliente {

    private Situacao situacao;
    private Integer pontos;

    @ConstructorProperties({"situacao", "pontos"})
    public SituacaoCliente(Situacao situacao, Integer pontos) {
        this.situacao = situacao;
        this.pontos = pontos;
    }

    public boolean estaInadimplente() {
        return situacao == null || situacao == Situacao.INADIMPLENTE;
    }
}
