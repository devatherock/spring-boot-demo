package io.github.devatherock.controller;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final VelocityEngine velocityEngine;

    @GetMapping(path = "/template/{user}", produces = "text/html")
    public String getTemplate(@PathVariable String user) {
        VelocityContext context = new VelocityContext();
        context.put("user", user);

        StringWriter writer = new StringWriter();
        velocityEngine.getTemplate("templates/template_1.html").merge(context, writer);

        return writer.toString();
    }
}
