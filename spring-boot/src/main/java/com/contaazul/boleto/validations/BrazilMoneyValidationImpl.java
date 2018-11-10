package com.contaazul.boleto.validations;

import com.contaazul.boleto.validations.annotations.BrazilMoneyValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Optional;

public class BrazilMoneyValidationImpl implements ConstraintValidator<BrazilMoneyValidation, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value).isPresent() && value.unscaledValue().toString().matches("[0-9]{0,15}");
    }
}