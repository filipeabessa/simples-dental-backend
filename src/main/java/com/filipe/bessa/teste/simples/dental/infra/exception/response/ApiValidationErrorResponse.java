package com.filipe.bessa.teste.simples.dental.infra.exception.response;


import lombok.Getter;

@Getter
public class ApiValidationErrorResponse implements ApiSubErrorResponse {
    private final String object;
    private final String field;
    private final Object rejectedValue;
    private final String message;

    public ApiValidationErrorResponse(String object, String message) {
        this.object = object;
        this.field = "";
        this.rejectedValue = null;
        this.message = message;
    }

    public ApiValidationErrorResponse(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
