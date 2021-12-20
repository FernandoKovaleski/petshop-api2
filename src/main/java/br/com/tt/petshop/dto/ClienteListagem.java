package br.com.tt.petshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteListagem {

    private final Long id;
    private final String nome;
    private final String cpf;

}
