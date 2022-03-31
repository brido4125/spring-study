package jpabook.jpashop2;

import jpabook.jpashop2.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void save() {
        //given
        Member member = new Member();
        member.setUsername("brido");
        //when
        Long save = memberRepository.save(member);
        //then
        Member findMember = memberRepository.find(save);
        assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
    }
}