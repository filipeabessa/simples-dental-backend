package com.filipe.bessa.teste.simples.dental.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
