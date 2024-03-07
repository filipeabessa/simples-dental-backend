package com.filipe.bessa.teste.simples.dental.infra.exception.response;

import lombok.Getter;

@Getter
public class SubBusinessErrorResponse implements ApiSubErrorResponse{
    private final String code;
    private final String message;

    public SubBusinessErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
