package hello.core2.order;

import hello.core2.annotation.MainDiscountPolicy;
import hello.core2.discount.DiscountPolicy;
import hello.core2.member.Member;
import hello.core2.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;
    /*
    * 의존관계 생성자 주입
    * 생성자 하나일 경우 @Autowired 생략 가능
    *  @Autowired
    *    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    *        this.memberRepository = memberRepository;
    *        this.discountPolicy = discountPolicy;
    *    }
    *
    * */



    /* 일반 메서드 주입 => filed에 final keyword를 없애야함.
     * 항상 역할(real instance)에 대한 변경이 public으로 열려 있어야하는 문제점 존재!
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    /* 수정자 주입 => filed에 final keyword를 없애야함.
     * 항상 구현에 대한 역할(real instance)에 대한 변경이 public으로 열려 있어야하는 문제점 존재!
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
