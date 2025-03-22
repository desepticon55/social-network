package dev.bd.work.socialnetwork.service;

import dev.bd.work.socialnetwork.config.SocialNetworkParamConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.util.Date;
import java.util.UUID;

/**
 * JWT generator.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private final Clock clock;
    private final SocialNetworkParamConfig config;

    /**
     * Generate token
     *
     * @param userId user id
     * @return token
     */
    public String generateToken(UUID userId) {
        SocialNetworkParamConfig.Jwt jwt = config.getJwt();
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(Date.from(clock.instant()))
                .setExpiration(Date.from(clock.instant().plusMillis(jwt.getExpirationTimeout())))
                .signWith(Keys.hmacShaKeyFor(jwt.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
