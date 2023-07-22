package io.github.devatherock.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimeService {

    public StreamingResponseBody getFile() throws FileNotFoundException {
        File file = new File("Path to the pdf file");
        FileInputStream instream = new FileInputStream(file);

        return out -> {
            instream.transferTo(out);
        };
    }
    
    public Object getData() {
        Map<String, String> someObject = new HashMap<>();
        someObject.put("someKey", "someValue");
        
        return someObject;
    }
}
