package br.com.tt.petshop.dto;

import java.time.LocalDateTime;

public class InformacoesErro {

    private final int codigo;
    private final LocalDateTime dataHora;
    private final String mensagem;

    public InformacoesErro(int codigo, LocalDateTime dataHora, String mensagem) {
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.mensagem = mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }
}
