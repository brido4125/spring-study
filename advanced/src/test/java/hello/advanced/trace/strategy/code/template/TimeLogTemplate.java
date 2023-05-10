package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class TimeLogTemplate {
    public void excute(Callback callback) {
        long startTime = System.currentTimeMillis();
        //biz logic start
        callback.call();
        //biz logic end
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
