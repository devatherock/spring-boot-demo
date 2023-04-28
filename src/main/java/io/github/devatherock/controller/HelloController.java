package io.github.devatherock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    
    @Autowired
    @Qualifier("accountsClient")
    private WebClient accountsClient;
    
    @Autowired
    @Qualifier("payrollClient")
    private WebClient payrollClient;

    @GetMapping("/hello")
    public String sayHello() {
    	accountsClient
    		.get()
    		.uri("https://www.google.com/")
    		.exchangeToMono((response) -> {
    			LOGGER.info("Response class: " + response.getClass().getName());
    			return response.bodyToMono(String.class);
    		})
    		.block();
    	
    	payrollClient
			.get()
			.uri("https://www.bing.com/")
			.exchangeToMono((response) -> {
				LOGGER.info("Response class: " + response.getClass().getName());
				return response.bodyToMono(String.class);
			})
			.block();
    		
        return "Hello at " + timeService.getTime();
    }

}
