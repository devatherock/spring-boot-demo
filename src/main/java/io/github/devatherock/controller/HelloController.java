package io.github.devatherock.controller;

import io.github.devatherock.entities.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final LocalValidatorFactoryBean validator;

    @PostMapping("/cars")
    public ResponseEntity<List<String>> validateCar(@RequestBody /* @Valid */ Car car) {
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        List<String> violationMessages = null;
        HttpStatus responseCode = HttpStatus.OK;

        if (!CollectionUtils.isEmpty(violations)) {
            responseCode = HttpStatus.BAD_REQUEST;
            violationMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        }

        return new ResponseEntity(violationMessages, responseCode);
    }
}
