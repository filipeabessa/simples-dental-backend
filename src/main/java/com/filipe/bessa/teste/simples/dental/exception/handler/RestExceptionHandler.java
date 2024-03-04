package com.filipe.bessa.teste.simples.dental.exception.handler;


import com.filipe.bessa.teste.simples.dental.exception.response.ApiErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger TECHNICAL_LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                ex
        );

        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String errorMessage = ex.getParameterName() + " parâmetro da requisição não informado";
        var apiError = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                errorMessage,
                ex
        );
        apiError.addValidationError(ex.getParameterName(), ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                ex
        );
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Corpo da requisição inválido",
                ex
        );
        apiError.setMessage("Corpo da requisição inválido");
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro ao escrever saída JSON",
                ex
        );
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                String.format(
                        "Não pode encontrar o método %s para a URL %s",
                        ex.getHttpMethod(),
                        ex.getRequestURL()
                ),
                ex
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex
    ) {
        Class<?> requiredType = ex.getRequiredType();
        String simpleName = requiredType != null ? requiredType.getSimpleName() : "unknown";

        var apiError = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                String.format(
                        "O parâmetro '%s' recebeu o valor '%s', que não é válido para o tipo '%s'",
                        ex.getName(),
                        ex.getValue(),
                        simpleName
                ),
                ex
        );
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConversionFailedException(
            ConversionFailedException ex
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Erro de conversão",
                ex
        );
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var apiError = new ApiErrorResponse(
                status,
                ex
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(
            Exception ex
    ) {
        var apiError = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro inesperado",
                ex
        );
        return buildResponseEntity(apiError);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiError) {
        TECHNICAL_LOGGER.error(
                "Exceção sendo capturada, APIErrorCode: {}, Mensagem: {}",
                apiError.getErrorCode(),
                apiError.getMessage()
        );

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
