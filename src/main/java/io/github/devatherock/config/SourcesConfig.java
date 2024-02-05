package io.github.devatherock.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.ZoneOffset;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Slf4j
@PropertySource(value = "file:config/sources.yml", factory = YamlPropertySourceFactory.class)
public class SourcesConfig {

    @Getter
    @Setter
    private Map<String, MetaData> sources;

    @Data
    public static class MetaData {
        private String cron;
        private boolean calculateCA;
        private boolean modifyOhlc;
        private String lastUpdatedTime;
        private ZoneOffset gmtOffset;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("Sources: {}", sources);
    }
}