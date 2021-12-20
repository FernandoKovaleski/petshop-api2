package br.com.tt.petshop.factory;

import br.com.tt.petshop.dto.AgendamentoCriacao;
import br.com.tt.petshop.dto.AgendamentoDetalhes;
import br.com.tt.petshop.dto.AgendamentoListagem;
import br.com.tt.petshop.model.Agendamento;

public class AgendamentoFactory {

    public static AgendamentoListagem criaAgendamentoListagem(Agendamento agendamento) {
        return new AgendamentoListagem(agendamento.getId(), agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim());
    }

    public static AgendamentoDetalhes criaAgendamentoDetalhes(Agendamento agendamento) {
        return new AgendamentoDetalhes(agendamento.getId(), agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim(), agendamento.getObservacoes());
    }

    public static Agendamento criaAgendamento(AgendamentoCriacao criacao) {
        return new Agendamento(null, criacao.getDataHoraInicio(),
                criacao.getDataHoraFim(), criacao.getObservacoes());
    }

}
