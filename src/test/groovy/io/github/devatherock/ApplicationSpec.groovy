package io.github.devatherock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@SpringBootTest
@TestPropertySource(properties = 'jpa.enabled=false')
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration)
class ApplicationSpec extends Specification {

    @Autowired
    ApplicationContext context

    void 'test application initialization'() {
        expect:
        context != null
    }
}
