package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //선언하는 곳은 추상화에 의존, 실제 할당하는 곳이 구현체에 의존 DIP를 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
