package io.github.devatherock.service;

import java.util.Date;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import io.github.devatherock.entities.Message;
import lombok.Getter;

@Component
public class MessageService {
    @Getter
    private int callCounter = 0;

    @Cacheable(value = "testCache", key = "#id")
    public Message getMessage(String id) {
        callCounter++;
        return new Message(id, new Date());
    }
}
