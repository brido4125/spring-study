package hello.core3;

import hello.core3.Order.OrderService;
import hello.core3.Order.OrderServiceImpl;
import hello.core3.discount.DiscountPolicy;
import hello.core3.discount.FixDiscountPolicy;
import hello.core3.discount.RateDiscountPolicy;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;
import hello.core3.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig == DI 컨테이너
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
