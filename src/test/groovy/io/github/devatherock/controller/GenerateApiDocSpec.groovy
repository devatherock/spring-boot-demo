package io.github.devatherock.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class GenerateApiDocSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    ObjectMapper objectMapper

    void 'test generate api doc'() {
        setup:
        Files.deleteIfExists(Paths.get('api-spec.json'))

        when:
        String apiSpec = restTemplate.getForObject('/v3/api-docs', String)

        then:
        def json = objectMapper.readValue(apiSpec, Map)
        json['paths']['/hello']

        cleanup:
        new File('api-spec.json') << apiSpec
    }
}
