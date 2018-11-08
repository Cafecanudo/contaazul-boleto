package com.contaazul.boleto.validations;

import com.contaazul.boleto.validations.annotations.BrazilMoneyValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class BrazilMoneyValidationImpl implements ConstraintValidator<BrazilMoneyValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value).isPresent() && value.matches("[0-9]{0,15}");
    }
}