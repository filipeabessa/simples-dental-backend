package com.filipe.bessa.teste.simples.dental.domain.exception;

import com.filipe.bessa.teste.simples.dental.infra.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProfessionalNotFoundException extends BusinessException {
    private static final String MESSAGE = "Professional not found";
    private static final Integer CODE = HttpStatus.NOT_FOUND.value();
    public ProfessionalNotFoundException() {
        super(CODE, MESSAGE);
    }
}
