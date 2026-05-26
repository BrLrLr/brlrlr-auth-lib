package io.brlrlr.lib.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.brlrlr.lib.auth.properties.JwtProperties;
import io.brlrlr.lib.auth.service.TokenProvider;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "custom.auth.jwt", name = "secret")
public class AuthAutoConfiguration {

	@Bean
    TokenProvider tokenProvider(JwtProperties jwtProperties) {
        return new TokenProvider(jwtProperties);
    }
}
