package io.github.devatherock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.entities.Message;
import io.github.devatherock.service.MessageService;

@RestController
public class MessageController {
    
    @Autowired
    private MessageService service;

    
    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable String id) {
        return service.getMessage(id);
    }
}
