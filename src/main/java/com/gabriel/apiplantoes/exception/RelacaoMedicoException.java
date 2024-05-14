package com.gabriel.apiplantoes.exception;

public class RelacaoMedicoException extends RuntimeException {

    public RelacaoMedicoException() {
        super("Medico ou unidade não encontrada!");
    }

    public RelacaoMedicoException(String message) {
        super(message);
    }

}
