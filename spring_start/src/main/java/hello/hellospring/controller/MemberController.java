package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // annotation 역할 :  spring bean으로 해당 클래스를 spring-container에서 관리
public class MemberController {

    private final MemberService memberService;

    @Autowired//Controller 와 Service 간의 연결 수행하는 어노테이션
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //submit 클릭 시 POST 요청 발생하며, 홈 화면으로 리다이렉트
    @PostMapping("members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        try{
            memberService.join(member);
        }catch(IllegalStateException exception){
                return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
