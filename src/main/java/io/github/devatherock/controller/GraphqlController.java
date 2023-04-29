package io.github.devatherock.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import io.github.devatherock.entities.ApiResponse;
import io.github.devatherock.service.ValueService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GraphqlController {
	private final ValueService valueService;

	@QueryMapping(name = "getValue")
	public ApiResponse getValue() {
		return ApiResponse.builder()
				.statusCode("OK")
				.message("Success")
				.payload(valueService.getValue())
				.build();
	}
}
