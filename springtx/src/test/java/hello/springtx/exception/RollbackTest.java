package hello.springtx.exception;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RollbackTest {

  @Autowired
  private RollbackService rollbackService;

  @Test
  void runtime() {
    Assertions.assertThatThrownBy(() -> {
      rollbackService.runtimeException();
    }).isInstanceOf(RuntimeException.class);
  }

  @Test
  void checked() {
    Assertions.assertThatThrownBy(() -> {
      rollbackService.checkedException();
    }).isInstanceOf(MyException.class);
  }

  @Test
  void rollbackFor() {
    Assertions.assertThatThrownBy(() -> {
      rollbackService.rollbackFor();
    }).isInstanceOf(MyException.class);
  }

  @TestConfiguration
  static class RollbackTestConfig {
    @Bean
    RollbackService rollbackService() {
      return new RollbackService();
    }
  }

  @Slf4j
  static class RollbackService {


    //Runtime Exception
    @Transactional
    public void runtimeException() {
      log.info("call runtimeException");
      throw new RuntimeException();
    }

    //Checked Exception
    @Transactional
    public void checkedException() throws MyException {
      log.info("call checkedException");
      throw new MyException();
    }

    //Checked Exception - rollbackFor
    @Transactional(rollbackFor = MyException.class)
    public void rollbackFor() throws MyException {
      log.info("rollbackFor checkedException");
      throw new MyException();
    }
  }


  static class MyException extends Exception {

  }
}
