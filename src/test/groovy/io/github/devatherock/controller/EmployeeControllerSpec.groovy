package io.github.devatherock.controller

import groovy.json.JsonOutput
import io.github.devatherock.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.Instant
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SpringBootTest(
        classes = Application, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EmployeeControllerSpec extends Specification {

    static final DateTimeFormatter DATE_FORMAT_DD_MM = DateTimeFormatter.ofPattern('dd-MM-')
            .withZone(ZoneId.systemDefault())
    static final DateTimeFormatter DATE_FORMAT_YYYY = DateTimeFormatter.ofPattern('yyyy')
            .withZone(ZoneId.systemDefault())

    @Autowired
    TestRestTemplate restTemplate

    void 'test say hello get'() {
        when:
        ResponseEntity response = restTemplate.exchange('http://localhost:8080/greeting/employee1?name=test',
                HttpMethod.GET, null, String)

        then:
        response.statusCode == HttpStatus.OK
        response.body == 'Welcome test'
    }

    void 'test say hello post for date of birth - #dateOfBirth'() {
        given:
        def requestBody = [
                'name'       : 'test',
                'dateOfBirth': dateOfBirth
        ]

        and:
        HttpHeaders headers = new HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        when:
        ResponseEntity response = restTemplate.exchange('http://localhost:8080/greeting/employee2',
                HttpMethod.POST,
                new HttpEntity(JsonOutput.toJson(requestBody), headers), String)

        then:
        response.statusCode == HttpStatus.OK
        response.body == expectedMessage

        where:
        expectedMessage       | dateOfBirth
        'Welcome test Senior' | '01-09-1984'
        'Welcome test Junior' | "${DATE_FORMAT_DD_MM.format(Instant.now())}${DATE_FORMAT_YYYY.format(Year.now().minusYears(16))}"
        'Welcome test Senior' | "${DATE_FORMAT_DD_MM.format(Instant.now())}${DATE_FORMAT_YYYY.format(Year.now().minusYears(18))}"
    }
}
