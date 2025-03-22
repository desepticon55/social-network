package dev.bd.work.socialnetwork.service;

import dev.bd.work.socialnetwork.exception.EntityNotFoundException;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(@NonNull UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class));
    }
}
