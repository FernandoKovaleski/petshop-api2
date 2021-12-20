package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired //No lugar do construtor, somente em TESTES!
    ClienteRepository clienteRepository;

    @Autowired
    TestEntityManager em;


    @Test
    void deveRetornarListaVazia(){
        List<Cliente> clientes = clienteRepository.findAll();
        Assertions.assertEquals(0, clientes.size());
    }

    @Test
    void deveRetornarClienteFulano(){
        em.persist(new Cliente(null, "Enzo", LocalDate.parse("2019-03-04"), "51 888-888", "631.975.590-37"));

        List<Cliente> clientes = clienteRepository.findAll();
        Assertions.assertEquals(1, clientes.size());
        assertClienteEnzo(clientes.get(0));
    }

    @Test
    void deveRetornarFulanoPorCpf(){
        em.persist(new Cliente(null, "Theo", LocalDate.parse("2020-01-03"), "51 99999999", "751.904.090-90"));
        em.persist(new Cliente(null, "Enzo", LocalDate.parse("2019-03-04"), "51 888-888", "631.975.590-37"));

        Cliente clientePorCpf = clienteRepository.findByCpf("631.975.590-37");
        Assertions.assertEquals("51 888-888", clientePorCpf.getTelefone());
        assertClienteEnzo(clientePorCpf);
    }

    @Test
    void deveRetornarPorCpfComJpql(){
        em.persist(new Cliente(null, "Theo", LocalDate.parse("2020-01-03"), "51 99999999", "751.904.090-90"));
        em.persist(new Cliente(null, "Enzo", LocalDate.parse("2019-03-04"), "51 888-888", "631.975.590-37"));

        Cliente clientePorCpf = clienteRepository
                .buscarPorCpfComTelefoneNaoNulo("631.975.590-37")
                .orElseThrow(() -> new RuntimeException("Não retornou o Enzo buscando pelo CPF"));

        Assertions.assertEquals("51 888-888", clientePorCpf.getTelefone());
        assertClienteEnzo(clientePorCpf);
    }

    @Test
    void deveBuscarPorCpfComTelefoneNulo(){
        em.persist(new Cliente(null, "Theo", LocalDate.parse("2020-01-03"), "51 99999999", "751.904.090-90"));
        em.persist(new Cliente(null, "Enzo", LocalDate.parse("2019-03-04"), null, "631.975.590-37"));

        Cliente clienteSemTelefone = clienteRepository.buscarPorCpfComTelefoneNulo("631.975.590-37");
        Assertions.assertNull(clienteSemTelefone.getTelefone());
        assertClienteEnzo(clienteSemTelefone);
    }

    @Test
    void deveContarClientes() {
        em.persist(new Cliente(null, "Theo", LocalDate.parse("2020-01-03"), "51 99999999", "751.904.090-90"));
        em.persist(new Cliente(null, "Enzo", LocalDate.parse("2019-03-04"), null, "631.975.590-37"));

        long quantidade = clienteRepository.quantidadeDeClientes();

        Assertions.assertEquals(2, quantidade);
    }

    @Test
    @Sql("classpath:inserir_clientes.sql")
    //@Sql(value = "classpath:limpar_banco.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveRetornarOsPrimeiros3PorNome(){
//        em.persist(new Cliente(null, "Theo", LocalDate.parse("2020-01-03"), null, "751.104.090-90"));
//        em.persist(new Cliente(null, "Enzo", LocalDate.parse("2019-03-05"), null, "631.275.590-37"));
//        em.persist(new Cliente(null, "João", LocalDate.parse("2020-01-06"), null, "751.304.090-90"));
//        em.persist(new Cliente(null, "Fábio", LocalDate.parse("2019-03-07"), null, "631.475.590-37"));
//        em.persist(new Cliente(null, "Giba", LocalDate.parse("2020-01-08"), null, "751.504.090-90"));
//        em.persist(new Cliente(null, "Antonio", LocalDate.parse("2019-03-09"), null, "631.675.590-37"));

        Cliente cliente = clienteRepository.findFirstByOrderByNomeAsc();

        Assertions.assertEquals("Antonio", cliente.getNome());
    }

    private void assertClienteEnzo(Cliente enzo){
        Assertions.assertEquals("Enzo", enzo.getNome());
        Assertions.assertEquals("631.975.590-37", enzo.getCpf());
        Assertions.assertEquals(LocalDate.parse("2019-03-04"), enzo.getNascimento());
    }

}
