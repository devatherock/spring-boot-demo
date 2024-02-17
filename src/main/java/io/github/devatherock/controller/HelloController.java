package io.github.devatherock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello at " + timeService.getTime();
    }

    @PostMapping("/images")
    public void createImage(
            @RequestParam String action,
            @RequestParam String image
    ) {
        LOGGER.info("Action: {}, Image size: {}", action, image.length());
    }
}
