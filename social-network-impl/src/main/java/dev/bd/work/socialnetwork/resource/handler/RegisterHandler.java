package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.RegisterUserRequest;
import dev.bd.work.socialnetwork.dto.RegisterUserResponse;
import dev.bd.work.socialnetwork.mapper.RegisterUserMapper;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler to register event.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class RegisterHandler {

    private final UserRepository userRepository;
    private final RegisterUserMapper registerUserMapper;

    /**
     * Register new user.
     *
     * @param request request
     * @return response
     */
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = userRepository.save(registerUserMapper.toDomainModel(request));
        return RegisterUserResponse.of(user.getId());
    }
}
