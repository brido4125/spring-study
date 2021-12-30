package hello.core2.order;

import hello.core2.discount.DiscountPolicy;
import hello.core2.discount.FixDiscountPolicy;
import hello.core2.discount.RateDiscountPolicy;
import hello.core2.member.Grade;
import hello.core2.member.Member;
import hello.core2.member.MemberRepository;
import hello.core2.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        /*
        * 생성자 주입 사용
        * */
        MemberRepository memberRepository = new MemoryMemberRepository();
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        memberRepository.save(new Member(1L, "brido", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(
                memberRepository, discountPolicy
        );
        Order order = orderService.createOrder(1L, "ItemA", 100000);

        //Discount 금액 검증
        assertThat(order.getDiscountPrice()).isEqualTo(order.getItemPrice() * 10 / 100);
        //할인된 금액 + 실제 금액 = 원래가격 검증
        int total = order.calculatePrice() + order.getDiscountPrice();
        assertThat(total).isEqualTo(order.getItemPrice());
    }
}