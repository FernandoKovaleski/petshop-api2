package br.com.tt.petshop.controller;

import br.com.tt.petshop.dto.ClienteAtualizacao;
import br.com.tt.petshop.dto.ClienteListagem;
import br.com.tt.petshop.service.ClienteService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {

    static final Logger LOG = LoggerFactory.getLogger(ClienteControllerTest.class);

    @Autowired
    MockMvc webclient;

    /*
    * @MockBean: Usem só em teste do Spring
    * Nos demais continue usando só @Mock
    */
    @MockBean
    ClienteService clienteService;

    @Test
    void deveRetornarListaVazia() throws Exception {
        webclient.perform(get("/clientes"))
                .andExpect(status().is(200));
    }

    @Test
    void deveRetornarDoisClientes() throws Exception {
        when(clienteService.listarClientes(null)).thenReturn(List.of(
                        new ClienteListagem(1L, "Thor Silva", "755.818.130-51"),
                        new ClienteListagem(2L, "Enzo Santos", "373.866.760-18")));

        webclient.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("size([*])").value(is(2)))
                //.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))// outra forma de ver o size
                .andExpect(jsonPath("$[0].id").value(is(1)))
                .andExpect(jsonPath("$[0].nome").value(is("Thor Silva")))
                .andExpect(jsonPath("$[0].cpf").value(is("755.818.130-51")))
                .andExpect(jsonPath("$[1].id").value(is(2)))
                .andExpect(jsonPath("$[1].nome").value(is("Enzo Santos")))
                .andExpect(jsonPath("$[1].cpf").value(is("373.866.760-18")))
                .andDo(print());
    }

    @Test
    void deveCriarERetornarLocation() throws Exception {

        when(clienteService.criar(Mockito.any())).thenReturn(22L);

        JSONObject json = new JSONObject();
        json.put("nome", "Gilberto");
        json.put("cpf", "8282828282");
        json.put("telefone", "51 999999");
        json.put("nascimento", "2021-12-07");

        webclient.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toString()))
        .andExpect(status().isCreated())
        .andExpect(header().stringValues("location", "/clientes/22"));
    }

    @Test
    void deveAtualizar() throws Exception{

        JSONObject json = new JSONObject();
        json.put("nome", "Thor Silva");
        json.put("telefone", "51 888888");
        json.put("nascimento", "1991-12-07");

        webclient.perform(put("/clientes/{id}", 22)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isNoContent());

        ArgumentCaptor<ClienteAtualizacao> captor = ArgumentCaptor.forClass(ClienteAtualizacao.class);
        verify(clienteService).atualizar(ArgumentMatchers.eq(22L), captor.capture());

        ClienteAtualizacao captura = captor.getValue();
        Assertions.assertEquals("Thor Silva", captura.getNome());
        Assertions.assertEquals("51 888888", captura.getTelefone());
        Assertions.assertEquals(LocalDate.parse("1991-12-07"), captura.getNascimento());
    }

    @Test
    void deveDeletar() throws Exception{

        webclient.perform(delete("/clientes/{id}", 22))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(clienteService).apagar(22L);
    }

}