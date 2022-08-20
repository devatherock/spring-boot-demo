package io.github.devatherock;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public VelocityEngine velocityEngine() {
        final VelocityEngine velocityEngine = new VelocityEngine();

        velocityEngine.setProperty("resource.loaders", "class");
        velocityEngine.setProperty("resource.loader.class.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        return velocityEngine;
    }
}
