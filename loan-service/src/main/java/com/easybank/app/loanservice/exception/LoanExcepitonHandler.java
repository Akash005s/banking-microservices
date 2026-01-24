package com.easybank.app.loanservice.exception;

import com.easybank.app.loanservice.constant.LoanExceptionConstant;
import com.easybank.app.loanservice.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Handles all exceptions globally
 */
@RestControllerAdvice
public class LoanExcepitonHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles business exceptions
     */
    @ExceptionHandler(LoanExcepiton.class)
    ResponseEntity<ErrorResponse> handleAccountExcepiton(LoanExcepiton ex){
        LoanExceptionConstant constant = ex.getExceptionConstant();

        ErrorResponse response =  ErrorResponse.builder()
                .errorCode(constant.getCode())
                .message(ex.getFinalErrorMessage())
                .status(constant.getStatus().value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, constant.getStatus());
    }

    /**
     * Handles validation errors (@Valid)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse response = ErrorResponse.builder()
                .errorCode(LoanExceptionConstant.INVALID_REQUEST.getCode())
                .message(errorMessage)
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles unknown / system exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        LoanExceptionConstant constant =
                LoanExceptionConstant.INTERNAL_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .errorCode(constant.getCode())
                .message(ex.getMessage())
                .status(constant.getStatus().value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, constant.getStatus());
    }
}
