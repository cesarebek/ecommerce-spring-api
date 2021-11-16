package com.cezarybek.ecommerce.exception;

import lombok.Data;

@Data
public class AuthenticationException extends RuntimeException {
    private final String message;
}
