package io.github.devatherock.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/hello")
    public Map<String, Object> sayHello() {
        return restTemplate.getForObject("http://localhost:8081/actuator/health", Map.class);
    }
}
