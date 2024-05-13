package com.gabriel.apiplantoes.exception;

public class UnidadeException extends RuntimeException {

    public UnidadeException() {
        super("Unidade não encontrada!");
    }

    public UnidadeException(String message) {
        super(message);
    }

}
