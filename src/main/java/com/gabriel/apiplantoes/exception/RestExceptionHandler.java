package com.gabriel.apiplantoes.exception;

import com.gabriel.apiplantoes.domain.dtos.RestErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnidadeException.class)
    private ResponseEntity<RestErrorDTO> unidadeExceptions(UnidadeException exception) {
        HttpStatus status;

        if (exception.getMessage().equals("Unidade não encontrada!")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        RestErrorDTO errorDTO = new RestErrorDTO(status, exception.getMessage());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(MedicoException.class)
    private ResponseEntity<RestErrorDTO> medicoExceptions(MedicoException exception) {
        HttpStatus status;

        if (exception.getMessage().equals("Medico não encontrado!")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        RestErrorDTO errorDTO = new RestErrorDTO(status, exception.getMessage());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(RelacaoMedicoException.class)
    private ResponseEntity<RestErrorDTO> medicoUnidadeExceptions(RelacaoMedicoException exception) {
        HttpStatus status;

        if (exception.getMessage().equals("Medico ou unidade não encontrada!"))  {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        RestErrorDTO errorDTO = new RestErrorDTO(status, exception.getMessage());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(EspecialidadeException.class)
    private ResponseEntity<RestErrorDTO> especialidadeExceptions(EspecialidadeException exception) {
        HttpStatus status;

        if (exception.getMessage().equals("Especialidade não encontrada!"))  {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        RestErrorDTO errorDTO = new RestErrorDTO(status, exception.getMessage());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(EnderecoException.class)
    private ResponseEntity<RestErrorDTO> especialidadeExceptions(EnderecoException exception) {
        HttpStatus status;

        if (exception.getMessage().equals("Endereço não encontrado!"))  {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        RestErrorDTO errorDTO = new RestErrorDTO(status, exception.getMessage());
        return ResponseEntity.status(status).body(errorDTO);
    }

}
