package io.github.devatherock.validator;

import io.github.devatherock.entities.Car;
import io.github.devatherock.entities.CarConstraint;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarValidator implements ConstraintValidator<CarConstraint, Car> {
    @Override
    public boolean isValid(Car car, ConstraintValidatorContext context) {
        return StringUtils.hasText(car.getModel());
    }
}
