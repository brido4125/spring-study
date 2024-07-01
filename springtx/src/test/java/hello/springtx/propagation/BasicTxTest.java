package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {

  @Autowired
  PlatformTransactionManager txManager;

  @TestConfiguration
  static class Config {
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
    }
  }

  @Test
  void commit() {
    log.info("TX start");
    TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("TX commit start");
    txManager.commit(status);
    log.info("TX commit complete");
  }

  @Test
  void rollback() {
    log.info("TX start");
    TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("TX rollback start");
    txManager.rollback(status);
    log.info("TX rollback complete");
  }

  @Test
  void double_commit() {
    log.info("TX1 start");
    TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("TX1 commit");
    txManager.commit(tx1);

    log.info("TX2 start");
    TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("TX2 commit");
    txManager.commit(tx2);
  }

  @Test
  void double_commit_rollback() {
    log.info("TX1 start");
    TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("TX1 commit");
    txManager.commit(tx1);

    log.info("TX2 rollback start");
    TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("TX2 rollback");
    txManager.rollback(tx2);
  }
}
