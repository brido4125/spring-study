package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
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

  @Test
  void inner_commit() {
    log.info("외부 TX 시작");
    TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("outer.isNewTx() = {}", outer.isNewTransaction()); // 처음 수행되는 TX ?

    log.info("내부 TX 시작");
    TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("inner.isNewTx() = {}", inner.isNewTransaction());
    log.info("내부 TX 커밋");
    txManager.commit(inner); // 아무런 동작 수행하지 않음

    log.info("외부 TX 커밋");
    txManager.commit(outer);
  }

  @Test
  void outer_rollback() {
    log.info("외부 TX 시작");
    TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

    log.info("내부 TX 시작");
    TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("내부 TX 커밋");
    txManager.commit(inner); // 아무런 동작 수행하지 않음

    log.info("외부 TX 롤백");
    txManager.rollback(outer); // 전체 로직이 롤백
  }

  @Test
  void inner_rollback() {
    log.info("외부 TX 시작");
    TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

    log.info("내부 TX 시작");
    TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("내부 TX 롤백");
    txManager.rollback(inner); //rollback-only marking

    log.info("외부 TX 롤백");

    Assertions.assertThatThrownBy(() -> {
      txManager.commit(outer);
    }).isInstanceOf(UnexpectedRollbackException.class);
  }

  @Test
  void inner_rollback_require_new() {
    log.info("외부 TX 시작");
    TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
    log.info("outer.isNewTx() = {}", outer.isNewTransaction()); // 처음 수행되는 TX ?

    log.info("내부 TX 시작");
    DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
    // defualt = REQUIRED => 기존 트랜잭션에 참여하는거 (뮬라 TX 1개)
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    TransactionStatus inner = txManager.getTransaction(definition);
    log.info("inner.isNewTx() = {}", inner.isNewTransaction());

    log.info("내부 TX 롤백");
    txManager.rollback(inner);

    log.info("외부 트랜잭션 커밋");
    txManager.commit(outer);
  }
}
