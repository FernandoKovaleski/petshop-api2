package br.com.tt.petshop.controller;

import br.com.tt.petshop.dto.AgendamentoAtualizacao;
import br.com.tt.petshop.dto.AgendamentoCriacao;
import br.com.tt.petshop.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static mock.AgendamentoMock.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AgendamentoController.class)
class AgendamentoControllerTest {

    @Autowired
    MockMvc webClient;

    @MockBean
    AgendamentoService agendamentoService;

    @Test
    void deveListarDoisAgendamentos() throws Exception {
        when(agendamentoService.listar()).thenReturn(
                List.of(criaAgendamentoListagemTarde(), criaAgendamentoListagemManha()));

        webClient.perform(get("/agendamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", equalTo(1)))
                .andExpect(jsonPath("$.[0].dataHoraInicio", equalTo("2021-02-01T14:00:00")))
                .andExpect(jsonPath("$.[0].dataHoraFim", equalTo("2021-02-01T15:00:00")))

                .andExpect(jsonPath("$.[1].id", equalTo(2)))
                .andExpect(jsonPath("$.[1].dataHoraInicio", equalTo("2023-04-15T09:00:00")))
                .andExpect(jsonPath("$.[1].dataHoraFim", equalTo("2023-04-15T10:30:00")));
    }

    @Test
    void deveBuscarPeloId() throws Exception {
        when(agendamentoService.buscarPorId(14L)).thenReturn(criaAgendamentoDetalhesTarde());

        webClient.perform(get("/agendamentos/{id}", 14))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(14)))
                .andExpect(jsonPath("$.dataHoraInicio", equalTo("2021-02-01T14:00:00")))
                .andExpect(jsonPath("$.dataHoraFim", equalTo("2021-02-01T15:00:00")));
    }


    @Test
    void deveCriar() throws Exception {
        when(agendamentoService.criar(any(AgendamentoCriacao.class))).thenReturn(77L);

        String jsonBody = criaBodyAgendamento();

        webClient.perform(post("/agendamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/agendamentos/77"));

        AgendamentoCriacao criacaoEsperada = new AgendamentoCriacao(AMANHA_AS_14_HORAS, AMANHA_AS_15_HORAS, "Banho e tosa");
        verify(agendamentoService).criar(criacaoEsperada);
    }
    
    @Test
    void deveApagar() throws Exception {
        webClient.perform(delete("/agendamentos/{id}", 88))
                .andExpect(status().isNoContent());

        verify(agendamentoService).apagar(88L);
    }

    @Test
    void deveAtualizar() throws Exception {

        String jsonBody = criaBodyAgendamento();

        webClient.perform(put("/agendamentos/{id}", 101)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isNoContent());

        AgendamentoAtualizacao atualizacao = new AgendamentoAtualizacao(AMANHA_AS_14_HORAS, AMANHA_AS_15_HORAS, "Banho e tosa");
        verify(agendamentoService).atualizar(101L, atualizacao);
    }

}