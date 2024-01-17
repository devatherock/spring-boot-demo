package io.github.devatherock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.service.TimeClient;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;
    private final TimeClient timeClient;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello at " + timeClient.getTime();
    }

    @GetMapping("/v1/time")
    public String getTime() {
        return timeService.getTime().toString();
    }
}
