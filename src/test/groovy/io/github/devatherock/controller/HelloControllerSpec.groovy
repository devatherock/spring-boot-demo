package io.github.devatherock.controller

import io.github.devatherock.entities.Car
import org.springframework.http.ResponseEntity
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import spock.lang.Specification
import spock.lang.Subject

class HelloControllerSpec extends Specification {
    @Subject
    HelloController controller

    void setup() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean()
        validator.afterPropertiesSet()
        controller = new HelloController(validator)
    }

    void 'test validate car'() {
        when:
        ResponseEntity<List<String>> response = controller.validateCar(new Car())

        then:
        response.statusCodeValue == 400
        response.body.size() == 1
    }
}
