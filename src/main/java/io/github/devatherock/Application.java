package io.github.devatherock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.devatherock.Application;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        LOGGER.info("Starting my graal app");
        SpringApplication.run(Application.class, args);
    }
}
