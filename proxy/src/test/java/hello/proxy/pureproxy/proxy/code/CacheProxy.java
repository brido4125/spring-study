package hello.proxy.pureproxy.proxy.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{

    private final Subject target;// 실제 Proxy가 요청을 전해야하는 대상, Proxy가 대신 및 호출하는 대상

    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("Proxy operation");

        /*
        * Cache Miss인 경우
        * */
        if(cacheValue == null) {
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}