package io.github.devatherock.config;

import java.time.Duration;
import java.util.function.Function;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Bean
	public ClientHttpConnector getClientHttpConnector() {
		return new ReactorClientHttpConnector(
				HttpClient.create()
				.wiretap(true)
				.metrics(true, Function.identity())
				.protocol(HttpProtocol.HTTP11)
				.secure(sslContextSpec -> 
					sslContextSpec
						.sslContext(getSslContext())
						.handshakeTimeout(Duration.ofSeconds(59)))
				);
	}

	@Bean
	@Primary
	public WebClient getWebClient(final ClientHttpConnector clientHttpConnector) {
		return WebClient.builder().baseUrl("https://www.google.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.clientConnector(clientHttpConnector).build();
	}
	
	private SslContext getSslContext() {
		try {
			return SslContextBuilder.forClient().build();
		} catch (SSLException e) {
			return null;
		}
	}
}