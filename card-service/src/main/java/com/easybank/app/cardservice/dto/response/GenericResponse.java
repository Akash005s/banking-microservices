package com.easybank.app.cardservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Generic API response wrapper
 *
 * @param <T> response data type
 */
@Getter
@AllArgsConstructor
public class GenericResponse<T> {

    private String message;
    private T data;
}
