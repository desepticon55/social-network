package dev.bd.work.socialnetwork.mapper;

import dev.bd.work.socialnetwork.dto.UserRegisterRequest;
import dev.bd.work.socialnetwork.model.User;
import lombok.Setter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Mapper from {@link UserRegisterRequest} to {@link User}.
 *
 * @author Alexey Bodyak
 */
@Mapper
public abstract class UserRegisterMapper implements AbstractDomainMapper<User, UserRegisterRequest> {

    @Setter
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User toDomainModel(UserRegisterRequest dto);

    @AfterMapping
    protected void afterMapping(UserRegisterRequest dto, @MappingTarget User user) {
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}
