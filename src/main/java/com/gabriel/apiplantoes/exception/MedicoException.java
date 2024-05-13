package com.gabriel.apiplantoes.exception;

public class MedicoException extends RuntimeException {

    public MedicoException() {
        super("Medico não encontrado!");
    }

    public MedicoException(String message) {
        super(message);
    }

}
