package io.github.devatherock.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimeService {

    @Cacheable("myCache")
    public String getTime() {
        LOGGER.info("In getTime method");
        return String.valueOf(System.currentTimeMillis());
    }
}
