package io.github.devatherock.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/v1")
public interface TimeClient {
    
    @Cacheable("time")
    @GetExchange("/time")
    String getTime();
}
