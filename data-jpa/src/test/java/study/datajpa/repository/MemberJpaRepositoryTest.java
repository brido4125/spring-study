package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)//실제 DB에 테스트 데이터를 집어 넣어줌
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("testA");
        Member save = memberJpaRepository.save(member);
        Member find = memberJpaRepository.find(save.getId());
        assertThat(save.getUsername()).isEqualTo(member.getUsername());
        assertThat(save.getUsername()).isEqualTo(find.getUsername());
        assertThat(find).isEqualTo(member);//동일한 Transaction 내에서는 동일한 객체를 보장 => 1차캐시 공부하기
    }
}