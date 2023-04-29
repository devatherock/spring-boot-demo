package io.github.devatherock.entities;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse {
	private String statusCode;
	private String message;
	
	@Builder.Default
	private String timestamp = Instant.now().toString();
	
	private StaticContent payload;
}