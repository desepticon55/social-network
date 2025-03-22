package dev.bd.work.socialnetwork.mapper;

import dev.bd.work.socialnetwork.dto.UserDto;
import dev.bd.work.socialnetwork.model.User;
import org.mapstruct.Mapper;

/**
 * Mapper from {@link User} to {@link UserDto}.
 *
 * @author Alexey Bodyak
 */
@Mapper
public interface UserDtoMapper extends AbstractDtoMapper<User, UserDto> {
}
