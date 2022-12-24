package hello.core3.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService statefulService1 = ac.getBean(StateFulService.class);
        StateFulService statefulService2 = ac.getBean(StateFulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        int price1 = statefulService1.getPrice();

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }
}


