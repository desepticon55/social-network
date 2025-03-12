package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.LoginRequest;
import dev.bd.work.socialnetwork.dto.LoginResponse;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.service.JwtGenerator;
import dev.bd.work.socialnetwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Login handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class LoginHandler {

    private final UserService userService;
    private final JwtGenerator jwtGenerator;
    private final PasswordEncoder passwordEncoder;

    /**
     * Login with specific credentials.
     *
     * @param request request
     * @return response
     */
    public LoginResponse login(LoginRequest request) {
        User user = userService.findUserById(request.getUserId());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AccessDeniedException("Invalid credentials");
        }
        return LoginResponse.of(jwtGenerator.generateToken(user.getId()));
    }
}
