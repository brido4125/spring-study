package hello.core2;

import hello.core2.discount.DiscountPolicy;
import hello.core2.discount.RateDiscountPolicy;
import hello.core2.member.MemberRepository;
import hello.core2.member.MemberService;
import hello.core2.member.MemberServiceImpl;
import hello.core2.member.MemoryMemberRepository;
import hello.core2.order.OrderService;
import hello.core2.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

}
