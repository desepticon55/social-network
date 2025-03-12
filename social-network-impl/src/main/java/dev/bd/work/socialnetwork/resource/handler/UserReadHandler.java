package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.UserDto;
import dev.bd.work.socialnetwork.mapper.UserDtoMapper;
import dev.bd.work.socialnetwork.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * User read handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class UserReadHandler {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    /**
     * Find user with specific id.
     *
     * @param id user identifier
     * @return user representation
     */
    public UserDto findUserById(@NonNull UUID id) {
        return userDtoMapper.toDto(userService.findUserById(id));
    }
}
