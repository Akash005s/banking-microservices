package com.easybank.app.cardservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CardExceptionConstant {

    CARD_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "Card not found"),
    CARD_ALREADY_EXISTS(1002, HttpStatus.CONFLICT, "Card already exists"),
    INVALID_REQUEST(1003, HttpStatus.BAD_REQUEST, "Invalid request data"),
    INTERNAL_ERROR(1004, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final int code;
    private final HttpStatus status;
    private final String defaultMessage;
}
