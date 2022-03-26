package brido.me.todo.service;

import brido.me.todo.model.UserEntity;
import brido.me.todo.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(final UserEntity userEntity) {
        if (userEntity == null || userEntity.getEmail() == null) {
            throw new RuntimeException("Entity error");
        }
        final String email = userEntity.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}",email);
            throw new RuntimeException("Email already exists");
        }
        log.info("Create New User : {}", email);
        return userRepository.save(userEntity);
    }

    public UserEntity emailValidation(String email, String authKey) {
        UserEntity find = userRepository.findByEmail(email);
        if (find.getEmailAuthKey().equals(authKey)) {
            find.setEmailConfirm(true);
            userRepository.save(find);
        } else {
            log.warn("Email Auth key is Not Same");
        }
        return find;
    }

    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);

        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }
}
