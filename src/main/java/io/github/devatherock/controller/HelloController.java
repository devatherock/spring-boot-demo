package io.github.devatherock.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.config.AppConfig.Foo;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/hello")
    public String sayHello() {
    	kafkaTemplate.send("test-topic", "test-key", new Foo("test-message"));
        return "Hello at " + timeService.getTime();
    }

}
