package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // annotation 역할 :  spring bean으로 해당 클래스를 spring-container에서 관리
public class MemberController {

    private final MemberService memberService;

    @Autowired//Controller 와 Service 간의 연결 수행하는 어노테이션
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
