package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId; // 사용자가 직접 이용

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

}
