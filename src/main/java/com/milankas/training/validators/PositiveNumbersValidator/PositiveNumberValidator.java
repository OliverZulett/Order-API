package com.milankas.training.validators.PositiveNumbersValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveNumberValidator implements ConstraintValidator<PositiveNumberConstraint, Integer> {

    @Override
    public void initialize(PositiveNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(integer);
        if(integer == null) return true;
        return integer > 0;
    }

}
