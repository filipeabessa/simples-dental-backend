package com.filipe.bessa.teste.simples.dental.infra.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String errorMessage;

    public BusinessException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode.toString();
        this.errorMessage = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode.toString();
        this.errorMessage = errorMsg;
    }
}
