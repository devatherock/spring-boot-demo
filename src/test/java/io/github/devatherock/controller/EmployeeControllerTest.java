package io.github.devatherock.controller;

import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.devatherock.Application;

@SpringBootTest(
        classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class EmployeeControllerTest {
    private static final DateTimeFormatter DATE_FORMAT_DD_MM = DateTimeFormatter.ofPattern("dd-MM-")
            .withZone(ZoneId.systemDefault());
    private static final DateTimeFormatter DATE_FORMAT_YYYY = DateTimeFormatter.ofPattern("yyyy")
            .withZone(ZoneId.systemDefault());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSayHelloGet() {
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/greeting/employee1?name=test",
                HttpMethod.GET, null, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Welcome test", response.getBody());
    }
    
    @MethodSource("prepareDataForTestSayHelloPost")
    @ParameterizedTest
    public void testSayHelloPost(String dateOfBirth, String expectedMessage) throws JsonProcessingException {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "test");
        requestBody.put("dateOfBirth", dateOfBirth);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/greeting/employee2",
                HttpMethod.POST,
                new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers), String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedMessage, response.getBody());
    }

    private static List<Arguments> prepareDataForTestSayHelloPost() {
        List<Arguments> arguments = new ArrayList<>();
        String currentDate = DATE_FORMAT_DD_MM.format(Instant.now());
        Year currentYear = Year.now();

        // Greater than 18
        String dateOne = "01-09-1984";
        arguments.add(Arguments.of(dateOne, "Welcome test Senior"));

        // Less than 18
        String dateTwo = currentDate + DATE_FORMAT_YYYY.format(currentYear.minusYears(16));
        arguments.add(Arguments.of(dateTwo, "Welcome test Junior"));

        // 18
        String dateThree = currentDate + DATE_FORMAT_YYYY.format(currentYear.minusYears(18));
        arguments.add(Arguments.of(dateThree, "Welcome test Senior"));

        return arguments;
    }
}
