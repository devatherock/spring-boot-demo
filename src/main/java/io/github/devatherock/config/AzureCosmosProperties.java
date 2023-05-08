package io.github.devatherock.config;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties("azure.cosmos")
public class AzureCosmosProperties {
	@NotBlank
    private String uri;

    @NotBlank
	private String key;

    @NotBlank
    private String secondaryKey;
}
