package io.github.devatherock.service

import java.time.Instant

import io.github.devatherock.ApplicationSpec
import org.springframework.beans.factory.annotation.Autowired

class TimeServiceSpec extends ApplicationSpec {

    @Autowired
    TimeService service

    void 'test get time'() {
        expect:
        service.getTime().compareTo(Instant.now()) <= 0
    }
}
