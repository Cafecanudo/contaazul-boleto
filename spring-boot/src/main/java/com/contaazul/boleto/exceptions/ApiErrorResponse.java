package com.contaazul.boleto.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorResponse {

    private int status;
    private int code;
    private String message;

}
