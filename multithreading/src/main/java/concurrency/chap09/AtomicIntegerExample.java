package concurrency.chap09;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
  private static AtomicInteger counter = new AtomicInteger(0);
  private static int NUM_THREAD = 5;

  public static void main(String[] args) throws InterruptedException {

    Thread[] threads = new Thread[NUM_THREAD];

    for (int i = 0; i < NUM_THREAD; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < 100000; j++) {
          counter.incrementAndGet();
        }
      });
    }
    Arrays.stream(threads).forEach(t -> t.start());
    Thread.sleep(500);
    System.out.println(counter.get());
  }
}
