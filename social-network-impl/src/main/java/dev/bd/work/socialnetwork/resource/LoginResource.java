package dev.bd.work.socialnetwork.resource;

import dev.bd.work.socialnetwork.dto.LoginRequest;
import dev.bd.work.socialnetwork.dto.LoginResponse;
import dev.bd.work.socialnetwork.resource.handler.LoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User resource.
 *
 * @author Alexey Bodyak
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginResource {

    private final LoginHandler authHandler;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authHandler.login(request);
    }
}
