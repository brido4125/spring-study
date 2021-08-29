package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        /*
        * A가 주문을하고 주문금액 조회하는 사이에
        * B가 주문을 해버린 상황
        * */

        //Thread A : A 사용자가 10000원 주문
        statefulService1.order("userA",10000);
        //Thread B : B 사용자가 20000원 주문
        statefulService2.order("userB",20000);

        //Thread A : A 사용자가 주문금액조회
        int price = statefulService1.getPrice();
        System.out.println("A의 price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
