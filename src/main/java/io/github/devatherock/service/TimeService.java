package io.github.devatherock.service;

import java.time.Instant;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimeService {

    public Instant getTime() {
        LOGGER.info("In getTime method");
        return Instant.now();
    }
}
