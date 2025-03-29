package dev.bd.work.socialnetwork.service;

import static dev.khbd.interp4j.core.Interpolations.s;

import dev.bd.work.socialnetwork.exception.EntityNotFoundException;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Find user by id.
     *
     * @param userId user id
     * @return user
     */
    public User findUserById(@NonNull UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class));
    }

    /**
     * Find all users by first name and second name.
     *
     * @param firstName  first name
     * @param secondName second name
     * @return list of users
     */
    public List<User> findAllUsersByName(@NonNull String firstName, @NonNull String secondName) {
        return userRepository.findAllByFirstNameAndSecondNamePrefix(s("${firstName}%"), s("${secondName}%"));
    }
}
