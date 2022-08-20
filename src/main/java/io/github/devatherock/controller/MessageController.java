package io.github.devatherock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.entities.Message;
import io.github.devatherock.service.MessageService;
import io.github.devatherock.util.CacheUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;
    private final CacheUtil cacheUtil;

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable String id) {
        cacheUtil.logCacheMetrics();

        return service.getMessage(id);
    }

}
