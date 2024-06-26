package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class TxBasicTest {

    @Autowired
    private BasicService basicService;

    @Test
    void proxyCheck() {
        log.info("AOP class = {}", basicService.getClass());
        boolean aopProxy = AopUtils.isAopProxy(basicService);
        Assertions.assertThat(aopProxy).isTrue();
    }

    @Test
    void txTest() {
        basicService.tx();
        basicService.notTx();
    }

    @TestConfiguration
    static class TxApplyBasicConfig{
        @Bean
        BasicService basicService() {
            return new BasicService();
        }
    }

    @Slf4j
    static class BasicService {

        @Transactional
        public void tx() {
            log.info("call tx");
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx Active = {}", txActive);
        }

        public void notTx() {
            log.info("call notTx");
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("not tx Active = {}", txActive);
        }


    }
}
