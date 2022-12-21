package hello.core3.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "brido", Grade.VIP);
        System.out.println("member = " + member);
        //when
        memberService.join(member);
        Member find = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(find);
    }
}
