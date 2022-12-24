package hello.core3.singleton;

import hello.core3.AppConfig;
import hello.core3.discount.DiscountPolicy;
import hello.core3.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1.조회 : 호출하면 객체 생성
        MemberService memberService1 = appConfig.memberService();
        System.out.println("memberService1 = " + memberService1);
        MemberService memberService2 = appConfig.memberService();
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용")
    void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        System.out.println("instance1 = " + instance1);
        SingletonService instance2 = SingletonService.getInstance();
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
        //same ==
        //equal -> equals
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1.조회 : 호출하면 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService1 = " + memberService1);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService1).isSameAs(memberService2);
    }
}
