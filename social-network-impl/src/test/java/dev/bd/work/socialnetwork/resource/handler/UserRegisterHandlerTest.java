package dev.bd.work.socialnetwork.resource.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import dev.bd.work.socialnetwork.AutoCloseableMockTest;
import dev.bd.work.socialnetwork.dto.UserRegisterRequest;
import dev.bd.work.socialnetwork.dto.UserRegisterResponse;
import dev.bd.work.socialnetwork.mapper.UserRegisterMapper;
import dev.bd.work.socialnetwork.model.User;
import dev.bd.work.socialnetwork.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Alexey Bodyak
 */
public class UserRegisterHandlerTest extends AutoCloseableMockTest {

    private UserRegisterHandler registerHandler;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        UserRegisterMapper mapper = Mappers.getMapper(UserRegisterMapper.class);
        mapper.setPasswordEncoder(new BCryptPasswordEncoder());
        registerHandler = new UserRegisterHandler(userRepository, mapper);
    }

    @Test
    public void registerUser_someCondition_createUserAndReturnResponse() {
        doAnswer(it -> it.<User>getArgument(0)).when(userRepository).save(any(User.class));

        UserRegisterResponse response = registerHandler.registerUser(
                new UserRegisterRequest().setFirstName("Alex").setPassword("secret")
        );

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captor.capture());
        User savedUser = captor.getValue();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getFirstName()).isEqualTo("Alex");
        assertThat(savedUser.getPassword()).isNotNull();
        assertThat(savedUser.getPassword()).isNotEqualTo("secret");
        assertThat(response.getUserId()).isEqualTo(savedUser.getId());
    }
}
