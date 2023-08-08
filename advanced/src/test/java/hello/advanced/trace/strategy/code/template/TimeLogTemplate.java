package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;


// 전략 패턴의 v2 버전과 내용이 동일함,
// 인자로 콜백을 받아서 실행함
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
