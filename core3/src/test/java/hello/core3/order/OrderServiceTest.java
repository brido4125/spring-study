package hello.core3.order;

import hello.core3.Order.Order;
import hello.core3.Order.OrderServiceImpl;
import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberServiceImpl memberService = new MemberServiceImpl();
    OrderServiceImpl orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "brido", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
