package io.github.devatherock.controller;

import io.github.devatherock.entities.Car;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello at " + timeService.getTime();
    }

    @PostMapping("/cars")
    public void validateCar(@RequestBody @Valid Car car) {

    }
}
