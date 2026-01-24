package com.easybank.app.cardservice.exception;

import com.easybank.app.cardservice.constant.CardExceptionConstant;
import lombok.Getter;

/**
 * Custom exception for business logic errors
 */
@Getter
public class CardExcepiton extends RuntimeException{

    private final CardExceptionConstant exceptionConstant;

    public CardExcepiton(CardExceptionConstant exceptionConstant) {
        super(exceptionConstant.getDefaultMessage());
        this.exceptionConstant = exceptionConstant;
    }

    public CardExcepiton(CardExceptionConstant exceptionConstant, String customMessage) {
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
