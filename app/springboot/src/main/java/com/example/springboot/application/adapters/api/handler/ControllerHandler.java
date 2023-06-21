package com.example.springboot.application.adapters.api.handler;

import com.example.springboot.domain.dto.responses.error.ErrorResponse;
import com.example.springboot.domain.dto.responses.error.FieldErrorResponse;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException ex,
            WebRequest request) {

        List<FieldErrorResponse> errors = new ArrayList<>();

        ErrorResponse error = ErrorResponse.builder()
                .httpCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .internalCode(ex.getErrorCode())
                .errors(errors)
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
