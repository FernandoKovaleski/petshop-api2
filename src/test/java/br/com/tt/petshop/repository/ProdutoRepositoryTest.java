package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    TestEntityManager em;

    @BeforeEach
    void configuraMassa(){
        em.persist(criarProdutoRacao());
        em.persist(criaProdutoShampooo());
    }

    @Test
    void deveRetornarTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        assertEquals(2, produtos.size());

        Produto produto1 = produtos.get(0);
        assertEquals("Ração Animais Pequenos", produto1.getNome());
        assertEquals(BigDecimal.valueOf(25.67), produto1.getValor());
        assertTrue(produto1.isAtivo());

        Produto produto2 = produtos.get(1);
        assertEquals("Shampoo Cão Feliz", produto2.getNome());
        assertEquals(BigDecimal.valueOf(25.30), produto2.getValor());
        assertTrue(produto2.isAtivo());
    }

    @Test
    void deveBuscarPeloNomeShampoo(){
        Produto produto = produtoRepository.findByNome("Shampoo Cão Feliz");
        assertEquals("Shampoo Cão Feliz", produto.getNome());
    }

    @Test
    void deveFiltrarContendoRacao(){
        List<Produto> produtos = produtoRepository.findByNomeContaining("Ração");
        assertEquals(1, produtos.size());
        assertEquals("Ração Animais Pequenos", produtos.get(0).getNome());
    }

    @Test
    void deveBuscarAtivosPorNome(){
        em.persist(criaProdutoRacaoInativo());

        List<Produto> produtos =
                produtoRepository.listarProdutosAtivosPorNome("Ração Animais Pequenos");

        assertEquals(1, produtos.size(), "A lista deveria ter 1 elemento");
        assertEquals("Ração Animais Pequenos", produtos.get(0).getNome());
        assertEquals(BigDecimal.valueOf(25.67), produtos.get(0).getValor());
        assertTrue(produtos.get(0).isAtivo());
    }

    @Test
    void deveBuscarProdutosAtivosPorPreco(){
        em.persist(criaProdutoRacaoInativo());
        em.persist(criaProdutoAntiPulgas());

        List<Produto> produtos = produtoRepository.listarProdutosAtivosPorPreco();

        assertEquals(3, produtos.size());
        assertEquals("Pó Anti Pulgas", produtos.get(0).getNome());
        assertEquals("Shampoo Cão Feliz", produtos.get(1).getNome());
        assertEquals("Ração Animais Pequenos", produtos.get(2).getNome());
    }

    @Test
    void deveContarProdutosInativos(){
        em.persist(criaProdutoRacaoInativo());

        long contador = produtoRepository.contarProdutosInativos();

        assertEquals(1, contador);
    }

    private Produto criaProdutoAntiPulgas() {
        return new Produto(null, "Pó Anti Pulgas", BigDecimal.valueOf(12.70), true);
    }

    private Produto criaProdutoRacaoInativo() {
        return new Produto(null, "Ração Animais Pequenos", BigDecimal.valueOf(100), false);
    }

    private Produto criaProdutoShampooo() {
        return new Produto(null, "Shampoo Cão Feliz", BigDecimal.valueOf(25.30), true);
    }

    private Produto criarProdutoRacao() {
        return new Produto(null, "Ração Animais Pequenos", BigDecimal.valueOf(25.67), true);
    }
}
