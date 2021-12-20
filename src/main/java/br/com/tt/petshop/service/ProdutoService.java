package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.ProdutoAtualizacao;
import br.com.tt.petshop.dto.ProdutoCriacao;
import br.com.tt.petshop.dto.ProdutoDetalhes;
import br.com.tt.petshop.dto.ProdutoListagem;
import br.com.tt.petshop.exception.NaoExisteException;
import br.com.tt.petshop.factory.ProdutoFactory;
import br.com.tt.petshop.model.Produto;
import br.com.tt.petshop.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDetalhes buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoFactory::criaProdutoDetalhes)
                .orElseThrow(() -> new NaoExisteException("Não existe produto com esse ID!"));
    }

    public List<ProdutoListagem> listar() {
        return produtoRepository.findAll().stream()
                .map(ProdutoFactory::criaProdutoListagem)
                .collect(Collectors.toList());
    }

    public Long criar(ProdutoCriacao dto) {
        Produto produtoSalvo = produtoRepository.save(ProdutoFactory.criaProduto(dto));
        return produtoSalvo.getId();
    }

    public void apagar(Long id) {
        produtoRepository.deleteById(id);
    }

    public void atualizar(Long id, ProdutoAtualizacao dto) {
        //TODO - Rever com o lombok
        produtoRepository.save(ProdutoFactory.criaProduto(id, dto));
    }
}
