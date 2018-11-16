package com.contaazul.boleto.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiErrorResponse {

    private HttpStatus status;
    private int code;
    private String message;
    private String stack;

}
