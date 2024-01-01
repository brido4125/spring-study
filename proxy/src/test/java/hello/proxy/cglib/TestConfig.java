package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.jdkdynamic.code.A;
import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.B;
import hello.proxy.jdkdynamic.code.BImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public A a() {
        AImpl a = new AImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(A.class);
        enhancer.setCallback(new TimeMethodInterceptor(a));
        return (A) enhancer.create();
    }

    @Bean
    public B b() {
        return new BImpl();
    }
}
