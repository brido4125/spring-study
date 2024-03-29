package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

/*
* field에 Strategy를 생성자를 통해 주입받는 방식, 선 조립 후 실행 방식
* Context는 변하지 않는 부분
* 해당 클래스는 생성자를 통해 주입
* Spring DI -> 전략 패턴 사용
* 전략을 변경하는 것에 불리함
* */

@Slf4j
public class ContextV1 {
    private final Strategy strategy;//Context는 strategy 인터페이스에만 의존하고 있다 -> 내부 구현체에 대한 정보 필요 없음

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void excute() {
        long startTime = System.currentTimeMillis();
        //biz logic start
        strategy.call();
        //biz logic end
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
