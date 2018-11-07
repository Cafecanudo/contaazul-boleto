package com.contaazul.boleto.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NoResultExceptionApi.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noFound(NoResultExceptionApi except) {
        return ApiErrorResponse.builder().code(HttpStatus.NOT_FOUND.ordinal()).status(HttpStatus.NOT_FOUND).message(
                Optional.of(except.getMessage()).orElse(except.toString())).stack(except.toString()).build();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, UnexpectedTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse unknownException(ConstraintViolationException except) {
        return ApiErrorResponse.builder().code(HttpStatus.BAD_REQUEST.ordinal()).status(HttpStatus.BAD_REQUEST).message(
                Optional.of(except.getMessage()).orElse(except.toString())).stack(except.toString()).build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiErrorResponse unprocessableEntity(MethodArgumentNotValidException except) {
        return ApiErrorResponse.builder().code(HttpStatus.UNPROCESSABLE_ENTITY.ordinal()).status(HttpStatus.UNPROCESSABLE_ENTITY).message(
                Optional.of(except.getMessage()).orElse(except.toString())).stack(except.toString()).build();
    }

    /*@ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception except) {
        return ApiErrorResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).status(HttpStatus.INTERNAL_SERVER_ERROR).message(
                Optional.of(except.getMessage()).orElse(except.toString())).build();
    }*/
}
