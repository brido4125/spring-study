package hello.proxy.pureproxy.concrete;

import hello.proxy.pureproxy.concrete.code.ConcreteClient;
import hello.proxy.pureproxy.concrete.code.ConcreteLogic;
import hello.proxy.pureproxy.concrete.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {


    /*
    * ConcreteLogic는 구체 클래스(not interface)
    * 다음 테스트는 해당 구체클래스에 프록시 적용
    * */
    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
        concreteClient.execute();
    }

    @Test
    void proxyWithConcreteClass() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy proxy = new TimeProxy(concreteLogic);//실제 호출 대상을 인자로
        // Client의 생성자에 부모, 자식 타입 모두 주입 가능
        ConcreteClient concreteClient = new ConcreteClient(proxy);

        concreteClient.execute();
    }
}
