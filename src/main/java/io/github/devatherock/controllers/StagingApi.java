package io.github.devatherock.controllers;

import java.io.IOException;

import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StagingApi {
    @RequestMapping(value = "/batches/{batchId}", method = RequestMethod.PUT)
    public ResponseEntity<String> createOrReplaceBatch(
            @RequestHeader(value = "X-TENANT-ID", required = true) String tenantId,
            @Size(min = 1) @PathVariable("batchId") String batchId,
            @RequestPart("profile") String profile,
            @RequestPart(name = "file", required = false) MultipartFile file,
            @RequestHeader(name = "content-type", required = false) String contentType) throws IOException {
        LOGGER.info("Batch id: {}, Content-Type: {}, Profile: {}, File: {}", batchId, contentType, profile,
                new String(file.getBytes()));
        return ResponseEntity.ok(tenantId);
    }
}
