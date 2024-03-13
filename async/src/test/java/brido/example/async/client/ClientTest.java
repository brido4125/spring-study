package brido.example.async.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
public class ClientTest {

  @Autowired
  private Client client;

  @Test
  void testVoid() {
    client.callVoidMethod();
    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    System.out.println("Is Async?");
  }

  @Test
  void testFuture() throws ExecutionException, InterruptedException {
    Integer res = client.callFutureMethod();
    System.out.println("res = " + res);
  }

  @Test
  void testValue() {
    Assertions.assertThatThrownBy(() -> {
      client.callInteger();
    });
  }

  @Test
  void testComlFuture() throws InterruptedException {
    client.callCmplFutureMethod();
    Thread.sleep(500); // prevent shutdown
  }

  @Test
  void testAsyncLf() throws InterruptedException {
    client.listenalbeFuture();
    Thread.sleep(500); // prevent shutdown
  }
}
