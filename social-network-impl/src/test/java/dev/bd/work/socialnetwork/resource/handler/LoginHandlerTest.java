package dev.bd.work.socialnetwork.resource.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import dev.bd.work.socialnetwork.AutoCloseableMockTest;
import dev.bd.work.socialnetwork.dto.LoginRequest;
import dev.bd.work.socialnetwork.dto.LoginResponse;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.service.JwtGenerator;
import dev.bd.work.socialnetwork.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

/**
 * @author Alexey Bodyak
 */
public class LoginHandlerTest extends AutoCloseableMockTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private LoginHandler loginHandler;

    @Mock
    private UserService userService;
    @Mock
    private JwtGenerator jwtGenerator;

    @BeforeEach
    public void init() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        loginHandler = new LoginHandler(userService, jwtGenerator, passwordEncoder);
    }

    @Test
    public void login_userWasFoundAndPasswordIsCorrect_returnResponse() {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(userService.findUserById(userId)).thenReturn(makeUser(userId));
        when(jwtGenerator.generateToken(userId)).thenReturn(TOKEN);

        LoginResponse response = loginHandler.login(new LoginRequest().setUserId(userId).setPassword("secret"));

        assertThat(response.getToken()).isEqualTo(TOKEN);
    }

    @Test
    public void login_userWasFoundAndPasswordIsNotCorrect_throwException() {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(userService.findUserById(userId)).thenReturn(makeUser(userId));
        when(jwtGenerator.generateToken(userId)).thenReturn(TOKEN);

        assertThrows(
                AccessDeniedException.class,
                () -> loginHandler.login(new LoginRequest().setUserId(userId).setPassword("secret1111"))
        );
    }

    private User makeUser(UUID userId) {
        return new User()
                .setId(userId)
                .setFirstName("Alex")
                .setPassword("$2a$10$k3fhHwbQpGPWMh.c3w.I7OeuQGSRwv9dIVenSIRXXL0z2q7K3.nNy");
    }
}
