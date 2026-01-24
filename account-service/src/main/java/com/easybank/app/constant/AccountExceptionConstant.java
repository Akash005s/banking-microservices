package com.easybank.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Centralized exception constants for Account Service.
 * <p>
 * Error code ranges:
 * 1000–1099 → Account related errors
 * 1100–1199 → Customer related errors
 * 1200–1299 → Validation errors
 * 5000–5099 → System errors
 */
@Getter
@AllArgsConstructor
public enum AccountExceptionConstant {

    ACCOUNT_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "Account not found"),
    ACCOUNT_ALREADY_EXISTS(1002, HttpStatus.CONFLICT, "Account already exists"),
    CUSTOMER_NOT_FOUND(1003, HttpStatus.NOT_FOUND, "Customer not found"),
    INVALID_REQUEST(1004, HttpStatus.BAD_REQUEST, "Invalid request data"),
    INTERNAL_ERROR(1005, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final int code;
    private final HttpStatus status;
    private final String defaultMessage;
}
