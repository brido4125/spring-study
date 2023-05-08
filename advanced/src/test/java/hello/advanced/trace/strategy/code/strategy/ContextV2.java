package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

/*
* 생성자 주입 방식 X
* strategy를 parameter로 전달하는 방식
* */

@Slf4j
public class ContextV2 {

    public void excute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        //biz logic start
        strategy.call();
        //biz logic end
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
