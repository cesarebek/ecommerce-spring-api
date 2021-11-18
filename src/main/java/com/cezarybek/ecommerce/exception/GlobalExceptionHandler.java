package com.cezarybek.ecommerce.exception;

import com.cezarybek.ecommerce.dto.ErrorDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Handle specific exceptions
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDetailsDto> handleAuthenticationException(AuthenticationException e,
                                                                         WebRequest webRequest) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EcommerceException.class)
    public ResponseEntity<ErrorDetailsDto> handleEcommerceException(EcommerceException e,
                                                                    WebRequest webRequest) {
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, e.getStatus());
    }

    //Global exceptions
}
