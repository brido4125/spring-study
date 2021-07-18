package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {


    //private final MemberRepository memberRepository = new MemoryMemberRepository(); 배우가 직접 상대배우를 지정하는 책임까지 갖는 경우
    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;//인터페이스에만 의존하도록 코드 설정 -> 당연히 구현체가 없어서 NPE 발생한다


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
