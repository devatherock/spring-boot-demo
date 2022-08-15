package io.github.devatherock;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "env.name", havingValue = "dev")
    public Resource resourceDev() throws IOException {
        Resource resource = new ClassPathResource("logback-dev.xml");
        LOGGER.info(Files.readString(resource.getFile().toPath()));
        
        return resource;
    }

    @Bean
    @ConditionalOnMissingBean
    public Resource resourceStage(@Value("classpath:logback.xml") Resource resource) throws IOException {
        LOGGER.info(Files.readString(resource.getFile().toPath()));
        return resource;
    }
}
