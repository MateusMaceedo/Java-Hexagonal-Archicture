package com.example.springboot.application.adapters.api.handler;

import com.example.springboot.domain.dto.responses.error.ErrorResponse;
import com.example.springboot.domain.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ControllerHandlerTest {

    @Test
    void handleProductNotFoundException_shouldReturnErrorResponseWithCorrectValues() {

        ProductNotFoundException ex = new ProductNotFoundException("404-001", "Produto não encontrado");
        WebRequest request = mock(WebRequest.class);

        ControllerHandler controllerHandler = new ControllerHandler();
        ResponseEntity<com.example.springboot.domain.dto.responses.error.ErrorResponse> responseEntity = controllerHandler.handleProductNotFoundException(ex, request);
        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Produto não encontrado", errorResponse.getMessage());
        assertEquals("404-001", errorResponse.getInternalCode());
        assertEquals(0, errorResponse.getErrors().size());
    }
}
