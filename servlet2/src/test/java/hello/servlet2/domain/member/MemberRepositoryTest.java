package hello.servlet2.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("brido",25);
        //when
        Member save = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(save.getId());
        assertThat(findMember.getId()).isEqualTo(save.getId());
    }

    @Test
    void findById() {
        //given
        Member member1 = new Member("test1",25);
        memberRepository.save(member1);
        //when
        Member findById = memberRepository.findById(member1.getId());
        //then
        assertThat(member1).isEqualTo(findById);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("test1",25);
        Member member2 = new Member("test2",20);
        List<Member> membersList = new ArrayList<>();
        membersList.add(member1);
        membersList.add(member2);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> all = memberRepository.findAll();
        //then
        assertThat(membersList.size()).isEqualTo(all.size());//갯수 test
        assertThat(membersList).containsAll(all);//내부 요소 확인
    }

    @Test
    void clearStore() {
        //given
        Member member1 = new Member("test1",25);
        Member member2 = new Member("test2",20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        memberRepository.clearStore();
        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(0);
    }
}