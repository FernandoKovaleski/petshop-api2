package br.com.tt.petshop.factory;

import br.com.tt.petshop.dto.ProdutoAtualizacao;
import br.com.tt.petshop.dto.ProdutoCriacao;
import br.com.tt.petshop.dto.ProdutoDetalhes;
import br.com.tt.petshop.dto.ProdutoListagem;
import br.com.tt.petshop.model.Produto;

public class ProdutoFactory {
     public static ProdutoDetalhes criaProdutoDetalhes(Produto produto){
         return new ProdutoDetalhes(
                 produto.getId(),
                 produto.getNome(),
                 produto.getValor(),
                 produto.isAtivo());
     }

    public static ProdutoListagem criaProdutoListagem(Produto produto) {
         return new ProdutoListagem(
                 produto.getId(),
                 produto.getNome(),
                 produto.getValor());
    }

    public static Produto criaProduto(ProdutoCriacao dto) {
        return new Produto(null, dto.getNome(), dto.getValor(), dto.isAtivo());
    }

    //Overloading - sobrecarga - mesmo nome, argumentos diferentes :)
    public static Produto criaProduto(Long id, ProdutoAtualizacao dto) {
        return new Produto(id, dto.getNome(), dto.getValor(), dto.isAtivo());
    }
}
