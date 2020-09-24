package com.milankas.training.validators.CountryCodeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class CountryCodeValidator implements ConstraintValidator<CountryCodeConstraint, String> {

    @Override
    public void initialize(CountryCodeConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Locale l = new Locale("", "CH");
        System.out.println(l.getDisplayCountry());
        return true;
    }

}
