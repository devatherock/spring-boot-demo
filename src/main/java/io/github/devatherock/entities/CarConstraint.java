package io.github.devatherock.entities;

import io.github.devatherock.validator.CarValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ ElementType.TYPE })
@Constraint(validatedBy = CarValidator.class)
public @interface CarConstraint {
    String message() default "The supplier car is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
