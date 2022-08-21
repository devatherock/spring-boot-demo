package io.github.devatherock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles('local')
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = [Application])
class ApplicationSpec extends Specification {

    @Autowired
    ApplicationContext context

    void 'test application initialization'() {
        expect:
        context
    }
}
