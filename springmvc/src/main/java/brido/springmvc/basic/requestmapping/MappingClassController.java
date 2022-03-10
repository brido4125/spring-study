package brido.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 회원 목록 조회
     * @return
     */
    @GetMapping
    public String user() {
        return "GET - users";
    }

    @PostMapping
    public String addUser() {
        return "POST - user";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId) {
        return "GET - user";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable Long userId) {
        return "PATCH - user";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return "DELETE - user";
    }

    }
