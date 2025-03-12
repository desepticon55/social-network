package dev.bd.work.socialnetwork.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import dev.bd.work.socialnetwork.AutoCloseableMockTest;
import dev.bd.work.socialnetwork.exception.EntityNotFoundException;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Alexey Bodyak
 */
public class UserServiceTest extends AutoCloseableMockTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test
    public void findUserById_userExists_returnIt() {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(new User().setId(userId).setFirstName("Alex")));

        User user = userService.findUserById(userId);

        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getFirstName()).isEqualTo("Alex");
    }

    @Test
    public void findUserById_userNotExists_throwException() {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findUserById(userId));
    }

    @Test
    public void findUserById_userIdIsNull_throwException() {
        assertThrows(NullPointerException.class, () -> userService.findUserById(null));
    }

}
