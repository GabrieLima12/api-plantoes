package com.gabriel.apiplantoes.exception;

public class EspecialidadeException extends RuntimeException {

    public EspecialidadeException() {
        super("Especialidade não encontrada!");
    }

    public EspecialidadeException(String message) {
        super(message);
    }

}
