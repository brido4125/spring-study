package hello.proxy.pureproxy.concrete.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic concreteLogic;//실제 호출할 대상

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("TimeProxy.operation() start");
        long start = System.currentTimeMillis();
        String result = concreteLogic.operation();
        long ended = System.currentTimeMillis();
        log.info("TimeProxy.operation() end");
        log.info("TimeProxy.operation() elapsed time: {}", ended - start);
        return result;
    }
}
