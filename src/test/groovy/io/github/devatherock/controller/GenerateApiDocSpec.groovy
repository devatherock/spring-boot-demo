package io.github.devatherock.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class GenerateApiDocSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    void 'test generate api doc'() {
        setup:
        Files.deleteIfExists(Paths.get('api-spec.json'))

        when:
        String apiSpec = mockMvc.perform(MockMvcRequestBuilders.get('/v3/api-docs'))
            .andReturn().response.contentAsString

        then:
        def json = objectMapper.readValue(apiSpec, Map)
        json['paths']['/hello']

        cleanup:
        new File('api-spec.json') << apiSpec
    }
}
