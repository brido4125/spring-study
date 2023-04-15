package hello.core3.singleton;

import hello.core3.AppConfig;
import hello.core3.Order.OrderServiceImpl;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        Assertions.assertThat(memberRepository1).isEqualTo(memberRepository2);
        Assertions.assertThat(memberRepository).isEqualTo(memberRepository2);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        /*
        * AppConfig -> AppConfig$$EnhancerBySpringCGLIB으로 바뀜
        * AppConfig를 상속하는게 AppConfigCGLIB
        * 부모 타입으로 조회하면 자식 타입의 Bean들도 전부 조회됨
        * CGLIB에서 빈이 이미 컨테이너에 등록되어 있으면 해당 Bean 리턴
        * 그렇지 않을 경우에만 실제 빈을 생성해서 리턴(위에서는 MemberRepository)
        * */
        System.out.println("bean.getClass() = " + bean.getClass());

        /*
        * @Configuration을 제외하면 Singleton이 깨지면서 요청마다 새로운 객체를 생성한다
        * */
    }
}
