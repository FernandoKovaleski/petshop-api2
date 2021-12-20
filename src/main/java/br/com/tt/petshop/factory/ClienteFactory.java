package br.com.tt.petshop.factory;

import br.com.tt.petshop.dto.ClienteCriacao;
import br.com.tt.petshop.dto.ClienteDetalhes;
import br.com.tt.petshop.dto.ClienteListagem;
import br.com.tt.petshop.model.Cliente;

public class ClienteFactory {

    public static ClienteListagem criarClienteListagem(Cliente cliente){
        return new ClienteListagem(cliente.getId(),
                cliente.getNome(),
                cliente.getCpf());
    }

    public static ClienteDetalhes criarClienteDetalhes(Cliente cliente){
        return new ClienteDetalhes(cliente.getId(),
                cliente.getNome(), cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getNascimento());
    }

    public static Cliente criarCliente(ClienteCriacao clienteCriacao) {
        return new Cliente(null,
                clienteCriacao.getNome(),
                clienteCriacao.getNascimento(),
                clienteCriacao.getTelefone(),
                clienteCriacao.getCpf());
    }
}
