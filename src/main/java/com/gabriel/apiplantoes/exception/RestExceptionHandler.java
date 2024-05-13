package com.gabriel.apiplantoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnidadeException.class)
    private ResponseEntity<RestErrorDTO> unidadeNaoEncontrada(UnidadeException exception) {
        RestErrorDTO errorDTO = new RestErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(MedicoException.class)
    private ResponseEntity<RestErrorDTO> medicoNaoEncontrado(MedicoException exception) {
        RestErrorDTO errorDTO = new RestErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

}
