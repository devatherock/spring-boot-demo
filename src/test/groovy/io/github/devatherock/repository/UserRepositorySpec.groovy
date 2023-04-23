package io.github.devatherock.repository

import io.github.devatherock.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.sql.Date
import java.time.Instant
import java.time.temporal.ChronoUnit

@SpringBootTest
class UserRepositorySpec extends Specification {

    @Autowired
    UserRepository repository

    void setup() {
        long currentTime = System.currentTimeMillis()
        repository.saveAll([
                new User('1', new Date(currentTime)),
                new User('2', new Date(currentTime)),
                new User('3', new Date(currentTime)),
                new User('4', new Date(Instant.ofEpochMilli(currentTime)
                        .minus(1, ChronoUnit.DAYS)
                        .toEpochMilli()))
        ])
    }

    void cleanup() {
        repository.deleteAll()
    }

    void 'test find by id and created date'() {
        when:
        List<User> users = repository.findByIdAndCreatedDate(['1', '2', '4'])

        then:
        users.size() == 2
        users[0].id == '1'
        users[1].id == '2'
    }
}
