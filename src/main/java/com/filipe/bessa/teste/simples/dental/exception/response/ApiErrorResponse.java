package com.filipe.bessa.teste.simples.dental.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@JsonTypeName("apiErrorResponse")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ApiErrorResponse {
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    @JsonProperty("status")
    private HttpStatusCode status;

    @JsonProperty("codigoErro")
    private Integer errorCode;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("mensagemDetalhada")
    private String detailedMessage;

    @JsonProperty("subErros")
    private List<ApiSubErrorResponse> subErrors = new ArrayList<>();

    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
        this.errorCode = status.value();
    }

    public ApiErrorResponse(HttpStatusCode status, Throwable ex) {
        this();
        this.status = status;
        this.errorCode = status.value();
        this.message = ex.getMessage();
        this.detailedMessage = ex.getLocalizedMessage();
    }

    public ApiErrorResponse(HttpStatusCode status, String message, Throwable ex) {
        this();
        this.status = status;
        this.errorCode = status.value();
        this.message = message;
        this.detailedMessage = ex.getLocalizedMessage();
    }

    public void addSubError(ApiSubErrorResponse subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    public void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationErrorResponse(object, field, rejectedValue, message));
    }

    public void addValidationError(String object, String message) {
        addSubError(new ApiValidationErrorResponse(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
            fieldError.getObjectName(),
            fieldError.getField(),
            fieldError.getRejectedValue(),
            fieldError.getDefaultMessage()
        );
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    public void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage()
        );
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ConstraintViolation<?> constraintViolation) {
        this.addValidationError(
                constraintViolation.getRootBeanClass().getSimpleName(),
                ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString(),
                constraintViolation.getInvalidValue(),
                constraintViolation.getMessage()
        );
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public void addBusinessSubError(String errorCode, String message) {
        addSubError(new SubBusinessErrorResponse(errorCode, message));
    }
}
