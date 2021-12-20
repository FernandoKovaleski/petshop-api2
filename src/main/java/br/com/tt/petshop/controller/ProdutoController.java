package br.com.tt.petshop.controller;

import br.com.tt.petshop.dto.ProdutoAtualizacao;
import br.com.tt.petshop.dto.ProdutoCriacao;
import br.com.tt.petshop.dto.ProdutoDetalhes;
import br.com.tt.petshop.dto.ProdutoListagem;
import br.com.tt.petshop.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @Operation(description = "Lista todos os Produtos")
    public List<ProdutoListagem> listar(){
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoDetalhes buscarPorId(
            @PathVariable @Parameter(description = "Representa o ID do Produto") Long id){
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody ProdutoCriacao dto){
        Long id = produtoService.criar(dto);
        URI location = URI.create(String.format("/produtos/%s", id));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long id){
        produtoService.apagar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(
            @ApiResponse(responseCode = "204", description = "Executado com sucesso, sem retorno!")
    )
    public void atualizar(@PathVariable Long id, @RequestBody ProdutoAtualizacao dto){
        produtoService.atualizar(id, dto);
    }
}
