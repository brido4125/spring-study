package hello.advanced.trace.strategy;

 import hello.advanced.trace.strategy.code.strategy.ContextV1;
 import hello.advanced.trace.strategy.code.strategy.Strategy;
 import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
 import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test  {
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //biz logic
        log.info("biz logic1 start");
        log.info("biz logic1 end");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }
    private void logic2() {
        long startTime = System.currentTimeMillis();
        //biz logic
        log.info("biz logic2 start");
        log.info("biz logic2 end");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }


    /*
    * 전략 패턴 사용
    * */
    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.excute();


        StrategyLogic1 strategyLogic2 = new StrategyLogic1();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.excute();
    }

    @Test
    void strategyV2() {

        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("logic1");
            }
        });
        context1.excute();
        ContextV1 context2 = new ContextV1(() -> log.info("logic2"));
        context2.excute();
    }

    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(() -> log.info("logic1"));
        context1.excute();

        ContextV1 context2 = new ContextV1(() -> log.info("logic2"));
        context2.excute();

    }
 }
