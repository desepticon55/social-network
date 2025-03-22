package dev.bd.work.socialnetwork.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Application config.
 *
 * @author Alexey Bodyak
 */
@Configuration
public class ApplicationConfig {

    @Bean
    @ConfigurationProperties("social-network")
    public SocialNetworkParamConfig socialNetworkParamConfig() {
        return new SocialNetworkParamConfig();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
