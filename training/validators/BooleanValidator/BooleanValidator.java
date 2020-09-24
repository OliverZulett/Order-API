package com.milankas.training.validators.BooleanValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BooleanValidator implements ConstraintValidator<BooleanConstraint, String> {

    @Override
    public void initialize(BooleanConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String booleanField, ConstraintValidatorContext constraintValidatorContext) {
        if(booleanField == null) return true;
        return booleanField.matches("true|false|1|0");
    }

}
