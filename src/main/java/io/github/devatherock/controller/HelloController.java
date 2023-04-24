package io.github.devatherock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final TimeService timeService;

    /**
     * @see https://springdoc.org/#getting-started, http://www.masterspringboot.com/web/rest-services/swagger-ui-tutorial/
     */
    @Operation(summary = "Says hello and provides the time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formed the hello message", content = {
                    @Content(mediaType = "text/plain") }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello at " + timeService.getTime();
    }

}
