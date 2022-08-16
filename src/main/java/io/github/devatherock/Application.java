package io.github.devatherock;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        LOGGER.info("Dev resource: {}", Files.readString(resource.getFile().toPath()));

        return resource;
    }

    @Bean
    public Resource resourceDefault(@Value("file:logback.xml") Resource inputResource) {
        Resource resource = null;

        if (inputResource.exists()) {
            resource = inputResource;
        } else {
            resource = new ClassPathResource("logback-dev.xml");
        }

        return resource;
    }

    @Bean
    @ConditionalOnProperty(name = "env.name", havingValue = "stage")
    public Resource resourceStage(@Value("classpath:logback.xml") Resource resource) throws IOException {
        LOGGER.info("Stage resource: {}", Files.readString(resource.getFile().toPath()));
        return resource;
    }
}
