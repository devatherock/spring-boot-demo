package io.github.devatherock.controller;

import io.github.devatherock.entities.Employee;
import io.github.devatherock.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final GreetingService greetingService;

    @GetMapping("/greeting/employee1")
    public String sayHello(@RequestParam String name) {
        return greetingService.getGreeting(name);
    }

    @PostMapping("/greeting/employee2")
    public String sayHello(@RequestBody Employee employee) {
        return greetingService.getGreeting(employee);
    }
}
