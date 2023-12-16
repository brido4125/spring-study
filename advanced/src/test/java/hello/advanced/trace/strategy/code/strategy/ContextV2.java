package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

/*
* 생성자 주입 방식 X
* strategy를 parameter로 전달하는 방식
* */

@Slf4j
public class ContextV2 {

    //전략을 인자로 호출 시점에 주입 받는 형태 -> Template Callback (spring 내부에서만 사용되는 디자인 패턴)
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
