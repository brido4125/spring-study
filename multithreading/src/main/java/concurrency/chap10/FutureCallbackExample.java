package concurrency.chap10;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class FutureCallbackExample {
    public static void main(String[] args) {

        //pool 1이 비동기 작업 시작
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> {
            Thread.sleep(500);
            return 11;
        };

        Future<Integer> result = executorService.submit(callable);
        registerCallback(result, i -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("i = " + i);
        });
    }

    private static void registerCallback(Future<Integer> result, CallbackExample.Callback callback) {
        //pool 2이 비동기 작업 get() Blocking 된 후, callback 로직 실행
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            int res;
            try {
                res = result.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            callback.onComplete(res);
        });
    }
}
