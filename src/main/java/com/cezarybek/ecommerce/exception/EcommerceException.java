package com.cezarybek.ecommerce.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EcommerceException extends RuntimeException {
    private final String message;
    private final HttpStatus status;
}
