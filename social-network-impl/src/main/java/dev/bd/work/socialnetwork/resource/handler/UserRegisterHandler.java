package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.UserRegisterRequest;
import dev.bd.work.socialnetwork.dto.UserRegisterResponse;
import dev.bd.work.socialnetwork.mapper.UserRegisterMapper;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Handler to register user.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class UserRegisterHandler {

    private final UserRepository userRepository;
    private final UserRegisterMapper registerUserMapper;

    /**
     * Register new user.
     *
     * @param request request
     * @return response
     */
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        User user = userRepository.save(registerUserMapper.toDomainModel(request));
        return UserRegisterResponse.of(user.getId());
    }
}
