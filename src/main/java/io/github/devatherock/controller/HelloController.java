package io.github.devatherock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;
    private final WebClient webClient;

    @GetMapping("/hello")
    public String sayHello() {
    	webClient
    		.get()
    		.uri("/")
    		.exchangeToMono((response) -> {
    			LOGGER.info("Response class: " + response.getClass().getName());
    			return response.bodyToMono(String.class);
    		})
    		.block();

        return "Hello at " + timeService.getTime();
    }

}
