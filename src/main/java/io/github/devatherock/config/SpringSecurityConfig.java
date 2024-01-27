package io.github.devatherock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> {
            // To override configuration in ManagementWebSecurityAutoConfiguration
            registry
                    .requestMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated();
        });
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
