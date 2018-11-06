package com.contaazul.boleto.exceptions;

import javax.persistence.NoResultException;

public class NoResultExceptionApi extends NoResultException {

    public NoResultExceptionApi(String value) {
        super(value);
    }
}
