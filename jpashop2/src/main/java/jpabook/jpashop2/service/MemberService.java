package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDupMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getUsername());
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 멤버입니다.(동일한 username)");
        }
    }

    //회원 전체 조화
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Member member) {
        return memberRepository.findById(member.getId());
    }
}
