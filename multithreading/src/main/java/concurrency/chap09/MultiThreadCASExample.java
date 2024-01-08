package concurrency.chap09;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCASExample {
    private static AtomicInteger value = new AtomicInteger(0);
    private static int NUM_THREAD = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREAD];
        for (int i = 0; i < NUM_THREAD; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    //value의 값을 1씩 증가
                    int expectedValue = value.get();
                    int newValue = expectedValue + 1;
                    boolean flag = value.compareAndSet(expectedValue, newValue);
                    while (flag == false) {
                        //retry
                        expectedValue = value.get();
                        newValue = expectedValue + 1;
                        flag = value.compareAndSet(expectedValue, newValue);
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + expectedValue + ":" + newValue);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("value = " + value);
    }
}
