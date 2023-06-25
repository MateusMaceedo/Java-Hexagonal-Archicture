package com.example.springboot.domain.dto.responses.error;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class ErrorResponse {

    private Integer httpCode;
    private String message;
    private String internalCode;
    private List<FieldErrorResponse> errors;
}
