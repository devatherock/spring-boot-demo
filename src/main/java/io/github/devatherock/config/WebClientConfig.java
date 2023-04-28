package io.github.devatherock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	/*@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.build();
	}*/
	
	@Bean
    public WebClient accountsClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

        return builder.clone()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
	
	@Bean
    public WebClient payrollClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

        return builder.clone()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
