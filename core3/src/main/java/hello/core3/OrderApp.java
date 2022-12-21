package hello.core3;

import hello.core3.Order.Order;
import hello.core3.Order.OrderServiceImpl;
import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberServiceImpl memberService = new MemberServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "brido", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order.toString());
    }
}
