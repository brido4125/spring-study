package hello.core3.Order;

import hello.core3.discount.DiscountPolicy;
import hello.core3.discount.FixDiscountPolicy;
import hello.core3.member.Member;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private  MemberRepository memberRepository;
    //기존 코드는 인터페이스와 구체 클래스 둘 다를 의존 하고 있음 => DIP 위반
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discount);
    }
}
