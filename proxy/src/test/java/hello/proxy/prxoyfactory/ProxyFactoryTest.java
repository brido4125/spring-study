package hello.proxy.prxoyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import hello.proxy.jdkdynamic.code.A;
import hello.proxy.jdkdynamic.code.AImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;


/*
* Proxy Factory를 생성할 때, 생성자에 실제 호출할 target 인스턴스를 parameter로 넘긴다.
* proxy Factory는 해당 인스턴스 정보를 통해 proxy를 생성한다.
* 여기서 target -> interface인 경우 JDK dynamic proxy
* target -> concrete class인 경우는 CGLIB을 통해 proxy를 생성한다.
* */
@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());

        /*
        * save와 find에 따라 proxy의 동작이 다르게 설정해보기
        * */
        proxy.save();
        proxy.find();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxy factory를 사용할 때만 가능
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스가 있으면 CGLIB 사용")
    void concreteProxy() {
        ConcreteService target = new ConcreteService(); // 구체 클래스만 있는 경우
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());

        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 true로 주면 Interface를 통해 타겟 인스턴스를 생성하더라도 CGLIB 사용")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //인터페이스가 있지만, CGLIB을 사용하려고 하는 경우 사용되는 메서드
        proxyFactory.setProxyTargetClass(true); // CGLIB를 통해 proxy 기능 적용, 구체 클래스를 상속을 받아서 CGLIB 사용
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());

        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    void logAdviceTest() {
        A target = new AImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new LogAdvice());
        A proxy = (A) proxyFactory.getProxy();
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());

        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
