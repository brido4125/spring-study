package vol1.springbook.recache;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PoolTest {
    @Test
    void addNewTaskToPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                doSomething();
            });
        }
        Thread.sleep(3000);
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
        System.out.println("threadPool.getPoolSize() = " + threadPool.getPoolSize());
        System.out.println("threadPool.getCorePoolSize() = " + threadPool.getCorePoolSize());
        executorService.execute(() -> doSomething());
        executorService.execute(() -> doSomething());
        executorService.execute(() -> doSomething());
        executorService.execute(() -> doSomething());
        executorService.execute(() -> doSomething());
        executorService.execute(() -> doSomething());
    }

    void doSomething() {
        System.out.print("hi I'm ");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }
}
