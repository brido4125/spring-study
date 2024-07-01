package hello.springtx.apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV2Test {

    @Autowired
    private CallService callService;

    @Test
    void test() {
        callService.external();
    }

    @TestConfiguration
    static class TxApplyBasicConfig{

        @Bean
        CallService CallService() {
            return new CallService(new InternalService());
        }

        @Bean
        InternalService internalService() {
            return new InternalService();
        }
    }

    @RequiredArgsConstructor
    @Slf4j
    static class CallService {

        @Autowired
        private final InternalService internalService;

        public void external() {
            log.info("external call");
            internalService.internal();
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active = {}", txActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("readOnly active = {}", readOnly);
        }
    }

    @Slf4j
    static class InternalService {

        @Transactional
        public void internal() {
            log.info("internal  call");
        }
    }
}
