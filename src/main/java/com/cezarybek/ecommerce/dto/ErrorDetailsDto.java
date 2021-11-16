package com.cezarybek.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetailsDto {
    private final Date timestamp;
    private final String message;
    private final String details;
}
