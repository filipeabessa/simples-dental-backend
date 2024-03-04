package com.filipe.bessa.teste.simples.dental.exception.handler;

import com.filipe.bessa.teste.simples.dental.exception.response.ApiErrorResponse;
import domain.exception.ContactNotFoundException;
import domain.exception.ProfessionalNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Object> handleException(ContactNotFoundException e) {
        var apiError = new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                e
        );
        apiError.addBusinessSubError(e.getErrorCode(), e.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProfessionalNotFoundException.class)
    public ResponseEntity<Object> handleException(ProfessionalNotFoundException e) {
        var apiError = new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                e
        );
        apiError.addBusinessSubError(e.getErrorCode(), e.getMessage());

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiError) {
        LOGGER.error(
                "Exceção sendo capturada, APIErrorCode: {}, Mensagem: {}",
                apiError.getErrorCode(),
                apiError.getMessage()
        );

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}