package br.com.tt.petshop.service;

import br.com.tt.petshop.client.credito.CreditoApiClient;
import br.com.tt.petshop.client.credito.SituacaoCliente;
import br.com.tt.petshop.dto.ClienteAtualizacao;
import br.com.tt.petshop.dto.ClienteCriacao;
import br.com.tt.petshop.dto.ClienteDetalhes;
import br.com.tt.petshop.dto.ClienteListagem;
import br.com.tt.petshop.exception.NaoExisteException;
import br.com.tt.petshop.exception.NegocioException;
import br.com.tt.petshop.factory.ClienteFactory;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CreditoApiClient creditoApiClient;

    public List<ClienteListagem> listarClientes(String nome){

        List<Cliente> clientes;

        if(nome == null){
            clientes = clienteRepository.findAll();
        }else{
            clientes = clienteRepository.findByNomeContaining(nome);
        }

        return clientes.stream()
                .map(ClienteFactory::criarClienteListagem)
                .collect(Collectors.toList());
    }

    //buscar por id com + detalhes
    public ClienteDetalhes buscarPorId(Long id){
        return clienteRepository.findById(id)
            .map(ClienteFactory::criarClienteDetalhes)
            .orElseThrow(() -> new NaoExisteException("O cliente informado não existe!"));
    }

    public Long criar(ClienteCriacao clienteCriacao) {

        SituacaoCliente situacao = creditoApiClient.obterSituacaoCredito(clienteCriacao.getCpf());

        log.info("Situação: {}", situacao);

        if(situacao.estaInadimplente()){
            throw new NegocioException("Usuário possui pendências e não pode ser cadastrado!");
        }

        Cliente cliente = ClienteFactory.criarCliente(clienteCriacao);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        //O Hibernate retorna o objeto cliente salvo com ID
        return clienteSalvo.getId();
    }

    public void apagar(Long id) {
        clienteRepository.deleteById(id);
    }

    public void atualizar(Long id, ClienteAtualizacao dto) {
        Cliente clienteASerAtualizado = clienteRepository.findById(id).orElseThrow();

        //TODO rever quando vermos lombok
        //TODO validar se o cliente existe
        Cliente clienteAtualizado = new Cliente(id,
                dto.getNome(),
                dto.getNascimento(),
                dto.getTelefone(),
                clienteASerAtualizado.getCpf());

        clienteRepository.save(clienteAtualizado);
    }

    @Transactional
    public void atualizar2(Long id, ClienteAtualizacao dto) {
        clienteRepository.findById(id)
                .ifPresent(cliente -> clienteRepository.delete(cliente));

        clienteRepository.save( new Cliente(id,
                        dto.getNome(),
                        dto.getNascimento(),
                        dto.getTelefone(),null));
    }
}
