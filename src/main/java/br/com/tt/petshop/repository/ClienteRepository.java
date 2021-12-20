package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@Repository - Não precisa colocar quando estender
//  uma interface do Spring Data!
public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

    //select distinct * from tb_cliente where cpf = %cpf% OR telefone = %2%
    // order by id
    List<Cliente> findDistinctByCpfOrTelefoneOrderById(String cpf, String telefone);

    Cliente findByCpf(String cpf);
    Cliente findByCpfAndTelefoneIsNotNull(String cpf);

    //Select * from tb_cliente where nome like '%n%'
    List<Cliente> findByNomeContaining(String nome);

    //Select * from tb_cliente where nome like 'n%'
    Cliente findByNomeStartingWith(String nome);

    //Select * from tb_cliente where nome like '%n'
    Cliente findByNomeEndingWith(String nome);

    List<Cliente> findByNascimentoBetween(LocalDate ini, LocalDate fim);

    // JPQL - validado em tempo de startup igual ao query method
    @Query("select cli from Cliente cli where cli.cpf = :cpf and cli.telefone is not null ")
    Optional<Cliente> buscarPorCpfComTelefoneNaoNulo(@Param("cpf") String cpf);

    //SQL - o SPring não valida em tempo de startup
    @Query(nativeQuery = true,
            value = "select id,nome,cpf,nascimento, nro_telefone as telefone from tb_cliente cli where cli.cpf = :cpf and cli.nro_telefone is null ")
    Cliente buscarPorCpfComTelefoneNulo(@Param("cpf") String cpf);

    @Query(nativeQuery = true, value = "select count(id) from tb_cliente")
    long quantidadeDeClientes();

    Cliente findFirstByOrderByNomeAsc();
}
