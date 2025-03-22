package dev.bd.work.socialnetwork.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * Social network configuration block.
 *
 * @author Alexey Bodyak
 */
@Getter
@Setter
@Validated
public class SocialNetworkParamConfig {

    @Valid
    @NotNull
    private Jwt jwt = new Jwt();

    @Getter
    @Setter
    public static class Jwt {
        @NotNull
        private String secretKey;
        @NotNull
        private Long expirationTimeout;
    }
}
