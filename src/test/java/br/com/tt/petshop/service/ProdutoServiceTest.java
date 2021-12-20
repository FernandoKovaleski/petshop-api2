package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.ProdutoDetalhes;
import br.com.tt.petshop.model.Produto;
import br.com.tt.petshop.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    ProdutoService produtoService;

    @Test
    void deveBuscarPorId(){
        Mockito.when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(new Produto(1L, "Shampoo Cão Pequeno", BigDecimal.valueOf(16.5), true)));

        ProdutoDetalhes produto = produtoService.buscarPorId(1L);
        assertNotNull(produto);
        assertEquals(1L, produto.getId());
        assertEquals("Shampoo Cão Pequeno", produto.getNome());
        assertEquals(BigDecimal.valueOf(16.5), produto.getValor());
        assertTrue(produto.isAtivo());
    }

    @Test
    void deveFalharAoBuscarIdInexistente(){
        Mockito.when(produtoRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class,
                () -> produtoService.buscarPorId(999L));

        assertEquals("Não existe produto com esse ID!",e.getMessage());
    }
}