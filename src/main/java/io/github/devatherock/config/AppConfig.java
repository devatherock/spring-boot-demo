package io.github.devatherock.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Slf4j
//@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final AbstractEnvironment environment;

    @Value("file:config/sources.yml")
    private Resource resource;

    @PostConstruct
    public void init() throws IOException {
        List<PropertySource<?>> propertySources = new YamlPropertySourceLoader().load("sources", resource);
        environment.getPropertySources().addLast(propertySources.get(0));
    }
}
