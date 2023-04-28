package io.github.devatherock.config;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
	
	/*@Autowired
	private WebClient.Builder builder;

	@PostConstruct
	public void init() {
		HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
		builder.clientConnector(new ReactorClientHttpConnector(httpClient));
	}*/
	
	@Bean
	public WebClientCustomizer timeoutCustomizer() {
		return (builder) -> {
			HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
			builder.clientConnector(new ReactorClientHttpConnector(httpClient));
		};
	}

	@Bean
	public GoogleService googleService(WebClient.Builder builder) {
		WebClient googleClient = builder
				.baseUrl("https://www.google.com")
				.build();

		return new GoogleService(googleClient);
	}

	@Bean
	public BingService bingService(WebClient.Builder builder) {
		WebClient bingClient = builder
				.baseUrl("https://www.bing.com")
				.build();

		return new BingService(bingClient);
	}

	@Getter
	@RequiredArgsConstructor
	public static class BingService {
		private final WebClient client;
	}

	@Getter
	@RequiredArgsConstructor
	public static class GoogleService {
		private final WebClient client;
	}
}