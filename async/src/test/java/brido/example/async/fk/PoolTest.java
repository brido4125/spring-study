package brido.example.async.fk;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class PoolTest {
  @Test
  public void parallel() {
    ForkJoinPool forkJoinPool = new ForkJoinPool(4);

    Object join = forkJoinPool.submit(() -> {
      int sum = IntStream.range(1, 10).parallel().peek(i -> {
        System.out.println(Thread.currentThread().getName());
      }).sum();
    }).join();

  }
}
