package com.contaazul.boleto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NoResultException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResultExceptionApi extends NoResultException {

    public NoResultExceptionApi(String value) {
        super(value);
    }
}
