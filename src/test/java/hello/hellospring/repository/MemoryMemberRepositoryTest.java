package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//public X, 만약 해당 테스트 클래스를 먼저 만들고 MemoryMemberRepository를 구현하면 TDD 방식이다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach//메서드 호출이 끝날 때 마다 호출되는 메서드
    public void afterEach(){
        memberRepository.clearStore();
        memberRepository.clearSequence();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("brido");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

    //public X, 만약 해당 테스트 클래스를 먼저 만들고 MemoryMemberRepository를 구현하면 TDD 방식이다.
    class MemoryMemberRepositoryTest {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();

        @AfterEach//메서드 호출이 끝날 때 마다 호출되는 메서드
        public void afterEach(){
            memberRepository.clearStore();
            memberRepository.clearSequence();
        }

        @Test
        public void save(){
            Member member = new Member();
            member.setName("brido");

            memberRepository.save(member);

            Member result = memberRepository.findById(member.getId()).get();

            assertThat(member).isEqualTo(result);
        }

        @Test
        public void findById(){
            Member member1 = new Member();
            memberRepository.save(member1);

            Member member2 = new Member();
            memberRepository.save(member2);

            Member result = memberRepository.findById(1L).get();
            assertThat(result).isEqualTo(member1);
        }

        @Test
        public void findByName(){
            Member member1 = new Member();
            member1.setName("spring1");
            memberRepository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            memberRepository.save(member2);

            Member result = memberRepository.findByName("spring1").get();
            assertThat(result).isEqualTo(member1);
        }

        @Test
        public void findAll(){

            Member member1 = new Member();
            member1.setName("spring1");
            memberRepository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            memberRepository.save(member2);

            List<Member> result = memberRepository.findAll();
            assertThat(result.size()).isEqualTo(2);
        }
    }


    @Test
    public void findById(){
        Member member1 = new Member();
        memberRepository.save(member1);

        Member member2 = new Member();
        memberRepository.save(member2);

        Member result = memberRepository.findById(1L).get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
