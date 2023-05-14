package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;


@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        //생성 되는 proxy 클래스는 ConcreteService를 상속받는다.
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass : {} ", target.getClass());
        log.info("proxyClass = {} ", proxy.getClass());

        proxy.call();
    }
}
