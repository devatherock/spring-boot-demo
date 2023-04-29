package io.github.devatherock.service;

import org.springframework.stereotype.Component;

import io.github.devatherock.entities.StaticContent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValueService {
	
	public StaticContent getValue() {
		return StaticContent.builder()
				.logo("http://logo")
				.name("en", "AAA")
				.name("ja", "aaa")
				.copyright("en", "AAA")
				.copyright("ja", "aaa")
				.help("http://help")
				.termsAndCondition("http://terms")
				.build();
	}
}
