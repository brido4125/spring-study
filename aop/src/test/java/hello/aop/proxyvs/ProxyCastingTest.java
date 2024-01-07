package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // false로 줄 경우 JDK 동적 프록시 사용

        //프록시를 인터페이스 타입으로 캐스팅
        MemberService proxy = (MemberService) proxyFactory.getProxy();
        //JDK 동적 프록시를 구현 클래로 캐스팅 할 경우 예외 발생 -> ClassCastException
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl impl = (MemberServiceImpl) proxy;});
    }

    @Test
    void cglib() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB를 사용한 프록시 생성

        //프록시를 인터페이스 타입으로 캐스팅
        MemberService proxy = (MemberService) proxyFactory.getProxy();
        //CGLIB를 구현 클래로 캐스팅 할 경우 성공
        MemberServiceImpl impl = (MemberServiceImpl) proxy;
    }
}
