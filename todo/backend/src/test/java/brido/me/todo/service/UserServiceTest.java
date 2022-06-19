package brido.me.todo.service;

import brido.me.todo.model.UserEntity;
import brido.me.todo.persistence.TodoRepository;
import brido.me.todo.persistence.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("userServiceTest")
class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }
    @Test
    void isNotNull() {
        assertThat(userRepository).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    void create() {
        //given
        String email = "Test@gmail.com";
        String userName = "Test-user";
        String password = "test";
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(password)
                .username(userName)
                .build();
        //when
        UserEntity userEntity = userService.create(user);
        //then
        Assertions.assertThat(userEntity.getEmail()).isEqualTo(email);
    }

    @Test
    void getByCredentials() {
    }
}