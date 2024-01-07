package hello.proxy.prxoyfactory;

import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.jdkdynamic.code.A;
import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public A a() {
        A target = new AImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor());
        return (A) proxyFactory.getProxy();
    }

    public static Advisor getAdvisor() {
        NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
        pointCut.setMappedNames("call");
        LogAdvice logAdvice = new LogAdvice();
        return new DefaultPointcutAdvisor(pointCut, logAdvice);
    }
}
