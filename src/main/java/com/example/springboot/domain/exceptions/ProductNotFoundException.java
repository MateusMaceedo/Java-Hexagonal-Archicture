package com.example.springboot.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    private final String errorCode;
    private final String message;

    public ProductNotFoundException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
