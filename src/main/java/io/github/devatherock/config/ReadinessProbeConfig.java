package io.github.devatherock.config;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadinessProbeConfig {

	@Bean
	public CompositeHealthContributor readinessProbe(
			@Qualifier("dbHealthContributor") Optional<HealthContributor> dbHealthContributor,
			@Qualifier("mongoHealthContributor") Optional<HealthContributor> mongoHealthContributor,
			Optional<DiskSpaceHealthIndicator> diskSpaceHealthIndicator) {
		Map<String, HealthContributor> healthIndicatorMap = Arrays
				.asList(dbHealthContributor, mongoHealthContributor, diskSpaceHealthIndicator)
				.stream()
				.filter(Optional::isPresent)
				.collect(Collectors.toMap(
						indicator -> indicator.get().getClass().getSimpleName(), Optional::get));

		return CompositeHealthContributor.fromMap(healthIndicatorMap);
	}
}
