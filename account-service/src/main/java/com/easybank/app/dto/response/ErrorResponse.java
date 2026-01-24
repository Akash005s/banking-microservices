package com.easybank.app.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Standard error response returned to client
 */
@Getter
@Builder
public class ErrorResponse {

    private int errorCode;
    private String message;
    private int status;
    private LocalDateTime timestamp;
}

