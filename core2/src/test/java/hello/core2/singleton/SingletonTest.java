package hello.core2.singleton;

import hello.core2.AppConfig;
import hello.core2.member.Member;
import hello.core2.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("Without Spring DI Container")
    void pureContainer() {
        //호출 할 때 마다 인스터스 새롭게 생성 => memory overhead가 너무 크다
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("Spring DI Container")
    void springContainer() {
        //호출 할 때 마다 인스터스 새롭게 생성 => memory overhead가 너무 크다
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    @DisplayName("Using Singleton Pattern")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //isEqualTo -> equal 비교
        //isSameAs -> == 비교
        assertThat(singletonService1).isSameAs(singletonService2);
    }
}
