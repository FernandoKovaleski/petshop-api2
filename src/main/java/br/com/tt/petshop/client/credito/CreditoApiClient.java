package br.com.tt.petshop.client.credito;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//SPel - Spring Expression Language - ${}
@FeignClient(name = "credito-api", url = "${client.credito.url}")
public interface CreditoApiClient {

    @GetMapping("/credito/{cpf}")
    SituacaoCliente obterSituacaoCredito(@PathVariable("cpf") String cpf);
}

