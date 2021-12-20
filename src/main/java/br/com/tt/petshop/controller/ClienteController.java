package br.com.tt.petshop.controller;

import br.com.tt.petshop.dto.ClienteAtualizacao;
import br.com.tt.petshop.dto.ClienteCriacao;
import br.com.tt.petshop.dto.ClienteDetalhes;
import br.com.tt.petshop.dto.ClienteListagem;
import br.com.tt.petshop.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j // Lombok para logs...
//@Validated // Essa anotação no Controller não deveria ser obrigatória...
@RestController //(@Controller + @ResponseBody)
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Read-retrieve
    @GetMapping //@RequestMapping(method = RequestMethod.GET)
    public List<ClienteListagem> listarClientes(@RequestParam(required = false) String nome) {
        return clienteService.listarClientes(nome);
    }

    //Qual o verbo?
    //Qual status code de sucesso? 200 OK (padrão)
    //Que tipo de parametro é o ID?
    //QUal a URL?
    @GetMapping("/{id}")
    public ClienteDetalhes buscarPorId(@PathVariable("id") Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity criaCliente(@RequestBody @Valid ClienteCriacao cliente) {


        log.info("Cliente criado: {}", cliente);
        Long idCriado = clienteService.criar(cliente);

        URI location = URI.create("/clientes/" + idCriado);
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        clienteService.apagar(id);
        //return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable("id") Long id,
                          @RequestBody ClienteAtualizacao cliente) {
        clienteService.atualizar(id, cliente);
    }
}
