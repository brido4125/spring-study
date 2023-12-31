package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        B bean = ac.getBean("beanA", B.class);
        bean.helloB();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(A.class));
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


    /*
    * 빈 후처리기 사용 -> BeanPostProcessor 인터페이스 구현
    * */
    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {}", beanName);
            log.info("bean = {}", bean);
            if (bean instanceof A) {
                log.info("A -> B");
                return new B();
            }
            return bean;
        }
    }

    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        //빈 후처리기도 스프링 빈으로 등록하여 사용
        @Bean
        public AToBPostProcessor aToBPostProcessor() {
            return new AToBPostProcessor();
        }
    }

}
