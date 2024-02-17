package test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        long start = System.currentTimeMillis();
        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();
        CompletableFuture<Integer> c = a.thenCombine(b, (z, y) -> z + y);

        executorService.submit(() -> a.complete(f(x)));
        executorService.submit(() -> b.complete(g(x)));

        /**
         * 최종 결과를 가져올 때만, Blocking
         * f,g 작업을 별도 스레드풀에게 맡길 수 있음
         */

        System.out.println(c.get()); //main thread가 Blocking
        long end = System.currentTimeMillis();
        System.out.println("Time = " + (end - start));
        executorService.shutdown();
    }

    public static int f(int x) {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return x * x;
    }

    public static int g(int x){
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return x * x;
    }
}
