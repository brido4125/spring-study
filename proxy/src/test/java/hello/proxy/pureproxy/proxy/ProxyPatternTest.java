package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import hello.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        Subject server = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(server);
        //총 3번의 호출 -> 3초
        client.excute();
        client.excute();
        client.excute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject sub = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(sub);//접근 제어의 목적으로 사용되는 proxy
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy); // proxy 주입
        //실제 객체는 한번만 호출
        client.excute();
        client.excute();
        client.excute();
    }
}
