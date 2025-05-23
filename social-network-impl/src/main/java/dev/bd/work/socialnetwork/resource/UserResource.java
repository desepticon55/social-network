package dev.bd.work.socialnetwork.resource;

import dev.bd.work.socialnetwork.dto.UserRegisterRequest;
import dev.bd.work.socialnetwork.dto.UserRegisterResponse;
import dev.bd.work.socialnetwork.dto.UserDto;
import dev.bd.work.socialnetwork.resource.handler.UserRegisterHandler;
import dev.bd.work.socialnetwork.resource.handler.UserReadHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    private final UserRegisterHandler registerHandler;
    private final UserReadHandler readHandler;

    @PostMapping("/register")
    public UserRegisterResponse registerUser(@RequestBody UserRegisterRequest request) {
        return registerHandler.registerUser(request);
    }

    @GetMapping("/get/{id}")
    public UserDto findUserById(@PathVariable("id") UUID id) {
        return readHandler.findUserById(id);
    }

    @GetMapping("/search")
    public List<UserDto> findAllUsersByFirstAndSecondName(@RequestParam("first_name") String firstName,
                                                          @RequestParam("second_name") String secondName) {
        return readHandler.findAllUsersByName(firstName, secondName);
    }
}
