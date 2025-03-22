package dev.bd.work.socialnetwork.mapper;

import dev.bd.work.socialnetwork.dto.RegisterUserRequest;
import dev.bd.work.socialnetwork.model.User;
import lombok.Setter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Mapper from {@link RegisterUserRequest} to {@link User}.
 *
 * @author Alexey Bodyak
 */
@Mapper
public abstract class RegisterUserMapper implements AbstractDomainMapper<User, RegisterUserRequest> {

    @Setter
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User toDomainModel(RegisterUserRequest dto);

    @AfterMapping
    protected void afterMapping(RegisterUserRequest dto, @MappingTarget User user) {
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}
