package com.milankas.training.validators.BooleanValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BooleanValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BooleanConstraint {
    String message() default "Invalid boolean";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
