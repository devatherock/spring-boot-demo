package io.github.devatherock.service;

import java.time.Instant;

import org.springframework.stereotype.Component;

import io.github.devatherock.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TimeService {
	private final PersonRepository personRepository;

    public Instant getTime() {
        LOGGER.info("In getTime method");
        return Instant.now();
    }
}
