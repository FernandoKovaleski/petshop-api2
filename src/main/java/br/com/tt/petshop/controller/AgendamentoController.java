package br.com.tt.petshop.controller;

import br.com.tt.petshop.dto.AgendamentoAtualizacao;
import br.com.tt.petshop.dto.AgendamentoCriacao;
import br.com.tt.petshop.dto.AgendamentoDetalhes;
import br.com.tt.petshop.dto.AgendamentoListagem;
import br.com.tt.petshop.service.AgendamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@AllArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoListagem> listar(){
        return agendamentoService.listar();
    }

    @GetMapping("/{id}")
    public AgendamentoDetalhes buscarPorId(@PathVariable("id") Long id){
        return agendamentoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid AgendamentoCriacao criacao){
        Long id = agendamentoService.criar(criacao);
        String location = String.format("/agendamentos/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable("id") Long id){
        agendamentoService.apagar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable("id") Long id,
                          @RequestBody @Valid AgendamentoAtualizacao atualizacao){
        agendamentoService.atualizar(id, atualizacao);
    }
}
