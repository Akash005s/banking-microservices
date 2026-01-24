package com.easybank.app.exception;

import com.easybank.app.constant.AccountExceptionConstant;
import lombok.Getter;

/**
 * Custom exception for business logic errors
 */
@Getter
public class AccountException extends RuntimeException{

    private final AccountExceptionConstant exceptionConstant;

    public AccountException(AccountExceptionConstant exceptionConstant) {
        super(exceptionConstant.getDefaultMessage());
        this.exceptionConstant = exceptionConstant;
    }

    public AccountException(AccountExceptionConstant exceptionConstant, String customMessage) {
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
