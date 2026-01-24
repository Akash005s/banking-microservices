package com.easybank.app.loanservice.exception;

import com.easybank.app.loanservice.constant.LoanExceptionConstant;
import lombok.Getter;

/**
 * Custom exception for business logic errors
 */
@Getter
public class LoanExcepiton extends RuntimeException {

    private final LoanExceptionConstant exceptionConstant;

    public LoanExcepiton(LoanExceptionConstant exceptionConstant) {
        super(exceptionConstant.getDefaultMessage());
        this.exceptionConstant = exceptionConstant;
    }

    public LoanExcepiton(LoanExceptionConstant exceptionConstant, String customMessage) {
        super(customMessage);
        this.exceptionConstant = exceptionConstant;
    }

    /**
     * Returns custom message if provided,
     * otherwise returns default message from enum.
     */
    public String getFinalErrorMessage() {
        return (super.getMessage() != null && !super.getMessage().isBlank())
                ? super.getMessage()
                : exceptionConstant.getDefaultMessage();
    }
}
