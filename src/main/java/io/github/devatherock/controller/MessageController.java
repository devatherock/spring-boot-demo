package io.github.devatherock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.entities.Message;
import io.github.devatherock.service.MessageService;
import io.github.devatherock.util.CacheUtil;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable String id) {
        CacheUtil.logCacheMetrics(meterRegistry);

        return service.getMessage(id);
    }

}
