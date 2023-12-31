package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig() {
        //스프링 컨테이너에서 빈을 직접 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);
        A a = ac.getBean("beanA", A.class);
        a.helloA();

        //B 오브젝트는 스프링 빈으로 등록되지 않음
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", B.class));
    }


    @Slf4j
    static class A {
        public void helloA() {
            log.info("helloA");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("helloB");
        }
    }

    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

}
