package hello.proxy.jdkdynamic;

import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.jdkdynamic.code.A;
import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.B;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class TestConfig {

    @Bean
    public A a() {
        AImpl a = new AImpl();
        return (A) Proxy.newProxyInstance(a.getClass().getClassLoader(), new Class[]{A.class}, new TimeInvocationHandler(a));
    }

    @Bean
    public B b() {
        return new BImpl();
    }
}
