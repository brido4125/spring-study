package hello.core2.order;

import hello.core2.discount.DiscountPolicy;
import hello.core2.member.Member;
import hello.core2.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    * 의존관계 생성자 주입
    * 생성자 하나일 경우 @Autowired 생략 가능
    * */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /* 수정자 주입 => filed에 final keyword를 없애야함.
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId,itemName,itemPrice,discount);
    }

    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
