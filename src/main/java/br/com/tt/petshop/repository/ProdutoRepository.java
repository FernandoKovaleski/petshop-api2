package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByNome(String nome);

    List<Produto> findByNomeContaining(String nome);

    //TODO Buscar produtos no intervalo de valor (mínimo, máximo)

    //JPQL - Listar produtos ativos filtrando por nome
    @Query("select pro from Produto pro where pro.ativo = true AND pro.nome = :nome ")
    List<Produto> listarProdutosAtivosPorNome(@Param("nome") String nome);

    @Query( value = "select * from tb_produto where ativo = true order by valor asc",
            nativeQuery = true)
    List<Produto> listarProdutosAtivosPorPreco();

    @Query( value = "select count(id) from tb_produto where ativo = false",
            nativeQuery = true)
    long contarProdutosInativos();
}
