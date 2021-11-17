package com.cezarybek.ecommerce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthResponseDto {
    private final String accessToken;
    private final Date issueDate;
    private final Date expirationDate;
    private final String tokenType;
}
