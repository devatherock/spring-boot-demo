package io.github.devatherock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.config.WebClientConfig.BingService;
import io.github.devatherock.config.WebClientConfig.GoogleService;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;
    private final GoogleService googleService;
    private final BingService bingService;

    @GetMapping("/hello")
    public String sayHello() {
    	googleService.getClient()
    		.get()
    		.uri("/")
    		.exchangeToMono((response) -> {
    			LOGGER.info("Response class: " + response.getClass().getName());
    			return response.bodyToMono(String.class);
    		})
    		.block();
    	
    	bingService.getClient()
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
