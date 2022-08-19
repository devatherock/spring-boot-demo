package io.github.devatherock.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager

import spock.lang.Specification

@SpringBootTest
class MessageServiceSpec extends Specification {

    @Autowired
    MessageService service

    @Autowired
    CacheManager cacheManager

    void setup() {
        cacheManager.getCache('testCache').clear()
    }

    void 'test get message'() {
        given:
        String id = 'hello'

        when:
        service.getMessage(id)

        then:
        service.callCounter == expectedResult

        where:
        expectedResult << [
            1,
            2
        ]
    }
}