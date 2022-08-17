package io.github.devatherock.service;

import java.util.Date;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import io.github.devatherock.entities.Message;

@Component
public class MessageService {

    @Cacheable(value = "testCache", key = "#id")
    public Message getMessage(String id) {
        return new Message(id, new Date());
    }
}
