package io.github.devatherock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class ApplicationSpec extends Specification {

    @Autowired
    ApplicationContext context

    void 'test application initialization'() {
        expect:
        context != null
    }
}
