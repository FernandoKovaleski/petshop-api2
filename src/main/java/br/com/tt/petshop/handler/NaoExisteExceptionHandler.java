package br.com.tt.petshop.handler;

import br.com.tt.petshop.dto.InformacoesErro;
import br.com.tt.petshop.exception.NaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class NaoExisteExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NaoExisteException.class)
    public InformacoesErro tratarErro(NaoExisteException e){
        return new InformacoesErro(
                404,
                LocalDateTime.now(),
                e.getMessage());
    }
}
