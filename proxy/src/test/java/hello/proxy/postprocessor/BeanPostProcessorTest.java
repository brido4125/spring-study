package hello.proxy.postprocessor;

import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.prxoyfactory.LogAdvice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {
    static class MyBeanPostProcessor implements BeanPostProcessor {
        private final Advisor advisor;

        public MyBeanPostProcessor(Advisor advisor) {
            this.advisor = advisor;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof A) {
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvisor(advisor); // pointcut + advice
                return proxyFactory.getProxy();
            }
            return bean;
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("helloA!!!!");
        }
    }

    @Configuration
    static class BeanPostProcessorConfig {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
        @Bean
        public Advisor advisor() {
            return new DefaultPointcutAdvisor(Pointcut.TRUE, new LogAdvice());
        }

        @Bean
        public MyBeanPostProcessor myBeanPostProcessor() {
            return new MyBeanPostProcessor(advisor());
        }

    }

    @Test
    void test() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        A beanA = (A) ac.getBean("beanA");
        beanA.helloA();
    }

}
