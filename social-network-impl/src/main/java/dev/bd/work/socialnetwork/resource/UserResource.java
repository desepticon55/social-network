package dev.bd.work.socialnetwork.resource;

import dev.bd.work.socialnetwork.dto.RegisterUserRequest;
import dev.bd.work.socialnetwork.dto.RegisterUserResponse;
import dev.bd.work.socialnetwork.dto.UserDto;
import dev.bd.work.socialnetwork.resource.handler.RegisterHandler;
import dev.bd.work.socialnetwork.resource.handler.UserReadHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * User resource.
 *
 * @author Alexey Bodyak
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final RegisterHandler registerHandler;
    private final UserReadHandler readHandler;

    @PostMapping("/register")
    public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest request) {
        return registerHandler.registerUser(request);
    }

    @GetMapping("/get/{id}")
    public UserDto findUserById(@PathVariable("id") UUID id) {
        return readHandler.findUserById(id);
    }
}
