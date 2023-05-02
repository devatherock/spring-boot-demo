package io.github.devatherock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.domain.InputEvent;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
	private final TimeService timeService;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello at " + timeService.getTime();
	}

	@PostMapping("/calendar/event")
    public void addEvent(@RequestBody InputEvent event) {
    	LOGGER.info("Transformed event: {}", event.toCalendarEvent().toString());
    }

}
