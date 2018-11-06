package com.contaazul.boleto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NoResultExceptionApi.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noFound(NoResultExceptionApi except) {
        return ApiErrorResponse.builder().code(404).status(404).message(
                Optional.of(except.getMessage()).orElse(except.toString())).build();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception except) {
        return new ApiErrorResponse(500, 500, Optional.of(except.getMessage()).orElse(except.toString()));
    }
}
