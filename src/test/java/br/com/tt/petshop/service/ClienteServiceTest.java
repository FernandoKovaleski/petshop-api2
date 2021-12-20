package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.ClienteDetalhes;
import br.com.tt.petshop.dto.ClienteListagem;
import br.com.tt.petshop.repository.ClienteRepository;
import mock.ClienteMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)//Liga o "plugin" do Mockito no teste do JUnit.
public class ClienteServiceTest {

    //@Autowired //NÃO EXISTE AUTOWIRED, Pois é SPRING, e não temos SPRING em teste de service
    @InjectMocks // dá NEW nessa classe e injeta os mocks (dependências)
    ClienteService clienteService;

    @Mock //Cria uma "simulação" da dependência
    ClienteRepository clienteRepository;

    @Test
    void deveListarClientes(){
        //Preparação (Arrange)
        when(clienteRepository.findAll()).thenReturn(List.of(ClienteMock.theo()));

        //Ação! (Act)
        List<ClienteListagem> dtos = clienteService.listarClientes(null);

        //Verificações (Assert)
        assertEquals(1, dtos.size());
        ClienteListagem cliente = dtos.get(0);
        assertEquals(1L, cliente.getId());
        assertEquals("Theo", cliente.getNome());
        assertEquals("751.904.090-90", cliente.getCpf());
    }

    @Test
    void deveRetornarPorId(){
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(ClienteMock.theo()));

        ClienteDetalhes cliente = clienteService.buscarPorId(1L);

        Assertions.assertNotNull(cliente);
        assertEquals(1L, cliente.getId());
        assertEquals("Theo", cliente.getNome());
        assertEquals("751.904.090-90", cliente.getCpf());
        assertEquals("51 9999 8888", cliente.getTelefone());
        assertEquals(LocalDate.parse("2018-10-03"), cliente.getNascimento());
    }

    @Test
    void deveFalharAoBuscarIdInexistente(){
        //O Mockito já retorna empty por padrão quando omitida a linha abaixo
        when(clienteRepository.findById(999L))
                .thenReturn(Optional.empty());

        RuntimeException exceptionDisparada = Assertions.assertThrows(
                RuntimeException.class,
                () -> clienteService.buscarPorId(999L));

        assertEquals("O cliente informado não existe!",
                exceptionDisparada.getMessage());
    }

}
