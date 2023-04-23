package io.github.devatherock.config;

import java.util.Map;

import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.DelegatingByTypeSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Configuration
public class AppConfig {

	@Bean
	public DefaultKafkaProducerFactoryCustomizer defaultKafkaProducerFactoryCustomizer() {
		return (producerFactory) -> {
			((DefaultKafkaProducerFactory<String, ?>) producerFactory).setKeySerializer(new StringSerializer());
			((DefaultKafkaProducerFactory<?, Object>) producerFactory)
					.setValueSerializer(new DelegatingByTypeSerializer(
							Map.of(byte[].class, new ByteArraySerializer(), Foo.class, new JsonSerializer<>())));
		};
	}

	@Getter
	@AllArgsConstructor
	public static class Foo {
		private String message;
	}
}
