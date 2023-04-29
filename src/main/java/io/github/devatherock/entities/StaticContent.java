package io.github.devatherock.entities;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class StaticContent {
	private String logo;
	
	@Singular("name")
	private Map<String, String> name;
	
	@Singular("copyright")
	private Map<String, String> copyrightText;
	
	private String help;
	
	private String termsAndCondition;
}