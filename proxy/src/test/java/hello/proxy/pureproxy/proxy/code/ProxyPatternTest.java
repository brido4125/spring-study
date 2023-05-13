package hello.proxy.pureproxy.proxy.code;

import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        Subject server = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(server);
        client.excute();
        client.excute();
        client.excute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject sub = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(sub);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
        client.excute();
        client.excute();
        client.excute();
    }
}
