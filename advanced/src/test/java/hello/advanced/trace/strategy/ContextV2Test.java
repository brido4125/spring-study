package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
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

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.excute(new StrategyLogic1());
        context.excute(new StrategyLogic2());
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
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("logic1");
            }
        };

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("logic2");
            }

        };

        ContextV1 context1 = new ContextV1(strategy1);
        context1.excute();

        ContextV1 context2 = new ContextV1(strategy2);
        context2.excute();

    }
 }
