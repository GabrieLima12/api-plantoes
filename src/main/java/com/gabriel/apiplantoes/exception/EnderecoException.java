package com.gabriel.apiplantoes.exception;

public class EnderecoException extends RuntimeException {

    public EnderecoException() {
        super("Endereço não encontrado!");
    }

    public EnderecoException(String message) {
        super(message);
    }
}
