package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.A;
import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.B;
import hello.proxy.jdkdynamic.code.BImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public A a() {
        return new AImpl();
    }

    @Bean
    public B b() {
        return new BImpl();
    }
}
