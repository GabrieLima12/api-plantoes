package com.gabriel.apiplantoes.exception;

public class MedicoUnidadeException extends RuntimeException {

    public MedicoUnidadeException() {
        super("Medico ou unidade não encontrada!");
    }

    public MedicoUnidadeException(String message) {
        super(message);
    }

}
