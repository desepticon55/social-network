package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.UserDto;
import dev.bd.work.socialnetwork.mapper.UserDtoMapper;
import dev.bd.work.socialnetwork.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = true)
    public UserDto findUserById(@NonNull UUID id) {
        return userDtoMapper.toDto(userService.findUserById(id));
    }

    /**
     * Find all users by first and second name
     *
     * @param firstName  first name
     * @param secondName second name
     * @return list of user representation
     */
    @Transactional(readOnly = true)
    public List<UserDto> findAllUsersByName(String firstName, String secondName) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(secondName)) {
            throw new IllegalArgumentException("User filter fields: firstName, secondName cannot be empty");
        }
        return userDtoMapper.toDto(userService.findAllUsersByName(firstName, secondName));
    }
}
