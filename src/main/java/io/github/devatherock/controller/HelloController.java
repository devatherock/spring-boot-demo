package io.github.devatherock.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService myService;

    @GetMapping(path = "/test", produces = "application/pdf")
    public ResponseEntity<StreamingResponseBody> getFile() throws FileNotFoundException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(myService.getFile());
    }

    @GetMapping(path = "/test", produces = "application/json")
    public Object getData() {
        return myService.getData();
    }
}
