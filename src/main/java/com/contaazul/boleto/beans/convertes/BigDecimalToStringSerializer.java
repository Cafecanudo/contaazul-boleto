package com.contaazul.boleto.beans.convertes;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;

public class BigDecimalToStringSerializer extends StdConverter<BigDecimal, String> {

    @Override
    public String convert(BigDecimal value) {
        return value.unscaledValue().toString();
    }
}
