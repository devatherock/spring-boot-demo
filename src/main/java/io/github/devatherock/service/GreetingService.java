package io.github.devatherock.service;

import io.github.devatherock.entities.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class GreetingService {
    public String getGreeting(String name) {
        return "Welcome " + name;
    }

    public String getGreeting(Employee employee) {
        String greeting;

        if (Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears() >= 18) {
            greeting = "Welcome " + employee.getName() + " Senior";
        } else {
            greeting = "Welcome " + employee.getName() + " Junior";
        }

        return greeting;
    }
}
