package com.contaazul.boleto.validations.annotations;

import com.contaazul.boleto.validations.BrazilMoneyValidationImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BrazilMoneyValidationImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BrazilMoneyValidation {
    String message() default "Invalid format money";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
