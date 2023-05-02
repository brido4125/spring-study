package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic1");
            }
        };

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic2");
            }
        };

        template1.execute();
        template2.execute();
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
}
