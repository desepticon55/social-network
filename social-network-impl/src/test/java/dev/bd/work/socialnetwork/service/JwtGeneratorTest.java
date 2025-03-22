package dev.bd.work.socialnetwork.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.bd.work.socialnetwork.AutoCloseableMockTest;
import dev.bd.work.socialnetwork.config.SocialNetworkParamConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

/**
 * @author Alexey Bodyak
 */
public class JwtGeneratorTest extends AutoCloseableMockTest {

    private static final String SECRET_KEY = "my-very-secure-key-256-bits-1234567890";
    private static final long EXPIRATION_TIMEOUT = 3600000; // 1 hour
    private static final Instant NOW = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    private JwtGenerator jwtGenerator;

    @Mock
    private Clock clock;
    @Mock
    private SocialNetworkParamConfig config;

    @BeforeEach
    public void init() {
        SocialNetworkParamConfig.Jwt jwt = new SocialNetworkParamConfig.Jwt();
        jwt.setExpirationTimeout(EXPIRATION_TIMEOUT);
        jwt.setSecretKey(SECRET_KEY);
        when(config.getJwt()).thenReturn(jwt);
        when(clock.instant()).thenReturn(NOW);
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());

        jwtGenerator = new JwtGenerator(clock, config);
    }

    @Test
    public void generateToken_tokenCanBeCreated_generateIt() {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        String token = jwtGenerator.generateToken(userId);

        assertThat(token).isNotNull();
        var claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        assertThat(claims.getSubject()).isEqualTo(userId.toString());
        assertThat(claims.getIssuedAt()).isEqualTo(Date.from(NOW));
        assertThat(claims.getExpiration()).isEqualTo(Date.from(NOW.plus(1L, ChronoUnit.HOURS)));
    }
}