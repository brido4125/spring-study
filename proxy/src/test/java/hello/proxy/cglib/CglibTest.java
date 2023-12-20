package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;


@Slf4j
public class CglibTest {

    /*
    * CGLIB은 구체 클래스와 인터페이스 모두 동적 proxy 생성 가능
    * */

    @Test
    @DisplayName("구체 클래스 프록시 테스트")
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);//CGLIB은 구체 클래스 기반의 프록시 생성 가능
        enhancer.setCallback(new TimeMethodInterceptor(target));
        //생성 되는 proxy 클래스는 ConcreteService를 상속받는다.
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());
        proxy.call();// proxy -> target 순으로 호출됨
    }

    @Test
    @DisplayName("인터페이스 프록시 테스트")
    void cglibInterfaceTest() {
        ServiceInterface target = new ServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceInterface.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        //생성 되는 proxy 클래스는 ConcreteService를 상속받는다.
        ServiceInterface proxy = (ServiceInterface) enhancer.create();
        //아래 로그먼저 호출
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());
        //Proxy가 호출되면서 Interceptor의 invoke가 호출되고 로그가 찍힌다.
        proxy.save();
    }
}
