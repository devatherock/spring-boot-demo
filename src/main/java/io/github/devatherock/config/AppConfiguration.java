package io.github.devatherock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.autoconfigure.cosmos.CosmosAutoConfiguration;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This configuration class is unused. Client is auto-configured using
 * {@link CosmosAutoConfiguration}
 * 
 * @author devaprasadh
 *
 */
@Slf4j
@RequiredArgsConstructor
public class AppConfiguration extends AbstractCosmosConfiguration {
	private final AzureCosmosProperties config;
	private AzureKeyCredential azureKeyCredential;

	@Bean
	public CosmosClientBuilder cosmosClient() {
		azureKeyCredential = new AzureKeyCredential(config.getKey());
		DirectConnectionConfig directConnectionConfig = new DirectConnectionConfig();
		GatewayConnectionConfig gatewayConnectionConfig = new GatewayConnectionConfig();
		return new CosmosClientBuilder().endpoint(config.getUri()).credential(azureKeyCredential)
				.directMode(directConnectionConfig, gatewayConnectionConfig);
	}

	@Override
	public CosmosConfig cosmosConfig() {
		return CosmosConfig.builder().enableQueryMetrics(true).maxDegreeOfParallelism(5).maxBufferedItemCount(100)
				.responseContinuationTokenLimitInKb(1024)
				.responseDiagnosticsProcessor(new ResponseDiagnosticsProcessorImplementation()).build();
	}

	public void switchToSecondaryKey() {
		this.azureKeyCredential.update(config.getSecondaryKey());
	}

	@Override
	protected String getDatabaseName() {
		return "testdb";
	}

	private static class ResponseDiagnosticsProcessorImplementation implements ResponseDiagnosticsProcessor {

		@Override
		public void processResponseDiagnostics(@Nullable ResponseDiagnostics responseDiagnostics) {
			LOGGER.info("Response Diagnostics {}", responseDiagnostics);
		}
	}

}