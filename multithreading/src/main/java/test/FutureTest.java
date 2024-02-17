package test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        long start = System.currentTimeMillis();
        CompletableFuture<Integer> a = new CompletableFuture<>();
        executorService.submit(() -> {a.complete(f(x));});
        int b = g(x); //main thread가 500ms 만큼 Blocking

        System.out.println(a.get() + b);//main thread가 Blocking
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
