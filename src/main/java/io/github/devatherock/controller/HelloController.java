package io.github.devatherock.controller;

import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;
    private final RestTemplate restTemplate;

    @GetMapping("/hello")
    public String sayHello() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("accept-encoding", "gzip");
            HttpEntity<Object> reqEntity = new HttpEntity<>(new HashMap<>(), headers);
            restTemplate.postForEntity("http://localhost:8080/users", reqEntity, Object.class);
        } catch(HttpStatusCodeException he) {
            LOGGER.error("Exception Status: {} -  {}", he.getStatusCode(), he.getStatusText());
            LOGGER.error("Exception - header {} ", he.getResponseHeaders());
            LOGGER.error("\n Exception - message  {} ", he.getResponseBodyAsString());
        }

        return "Hello at " + timeService.getTime();
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, Object> user) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "INVALID.INPUT.EXCEPTION");
        error.put("message", "Invalid field value");
        List<Map<String, String>> errors = new ArrayList<>();
        errors.add(error);

        Map<String, Object> response = new HashMap<>();
        response.put("transactionId", "063dfe4c-07c3-4dc1-b495-e0ae41520");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
