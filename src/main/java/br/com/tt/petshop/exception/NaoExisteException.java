package br.com.tt.petshop.exception;

/*
 * Anotação foi substituída pelo NaoExisteExceptionHandler
 * @ResponseStatus(HttpStatus.NOT_FOUND)
 */
public class NaoExisteException extends RuntimeException {

    public NaoExisteException(String message) {
        super(message);
    }
}
