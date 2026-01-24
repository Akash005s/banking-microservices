package com.easybank.app.loanservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LoanExceptionConstant {

    LOAN_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "Loan not found"),
    LOAN_ALREADY_EXISTS(1002, HttpStatus.CONFLICT, "Loan already exists"),
    INVALID_REQUEST(1003, HttpStatus.BAD_REQUEST, "Invalid request data"),
    INTERNAL_ERROR(1004, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final int code;
    private final HttpStatus status;
    private final String defaultMessage;
}
