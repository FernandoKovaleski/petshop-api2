package br.com.tt.petshop.handler;

import br.com.tt.petshop.dto.InformacoesErro;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public InformacoesErro trataErro(MethodArgumentNotValidException e){

        return new InformacoesErro(400,
                                    LocalDateTime.now(),
                                    e.getFieldError().getDefaultMessage()
                                    );
    }

}
