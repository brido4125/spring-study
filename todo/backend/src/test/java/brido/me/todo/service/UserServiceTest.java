package brido.me.todo.service;

import brido.me.todo.model.UserEntity;
import brido.me.todo.persistence.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserServiceTest")
class UserServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void create() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@email.com");
        userEntity.setPassword("test");
        userEntity.setUsername("brido");
        userEntity.setId("test");
        //when
        userService.create(userEntity);
        //then
        Assertions.assertThat(userRepository.existsByEmail(userEntity.getEmail())).isTrue();
    }
}