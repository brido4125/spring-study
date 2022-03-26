package brido.me.todo.controller;

import brido.me.todo.dto.ResponseDTO;
import brido.me.todo.dto.UserDTO;
import brido.me.todo.model.UserEntity;
import brido.me.todo.security.TokenProvider;
import brido.me.todo.service.EmailServiceImpl;
import brido.me.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;


    //이메일 인증 링크
    @GetMapping("/signup/confirm")
    public ResponseEntity<?> confirmUserByEmailLink(@RequestParam String email, @RequestParam String emailAuthKey) {
        UserEntity user = userService.emailValidation(email, emailAuthKey);

        UserDTO responseUserDTO = UserDTO.builder()
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .build();

        if (user.isEmailConfirm()) {
            return ResponseEntity.ok().body(responseUserDTO);
        }
        return ResponseEntity.badRequest().body(responseUserDTO);
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {

        try {
            //DTO를 통해 UserEntity 생성
            UserEntity user = UserEntity.builder()
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .emailConfirm(false)
                    .build();

            String confirm = emailService.sendSimpleMSG(userDto.getEmail());
            log.info("Key Number + {}",confirm);

            user.setEmailAuthKey(confirm);

            UserEntity registeredUser = userService.create(user);

            Map<String, String> map = new HashMap<String, String>();
            map.put("email", user.getEmail());
            map.put("authKey", user.getEmailAuthKey());
            log.info("email: {}, authKey : {}",user.getEmail(),user.getEmailAuthKey());

            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            ResponseDTO<Object> responseErrorDTO = ResponseDTO
                    .builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseErrorDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(), userDTO.getPassword(),passwordEncoder
        );
        if (user != null) {
            //token 생성
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .error("Login Failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
