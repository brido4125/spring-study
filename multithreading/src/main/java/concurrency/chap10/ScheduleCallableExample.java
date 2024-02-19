package concurrency.chap10;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleCallableExample {
    public static void main(String[] args) {
        ScheduledExecutorService ser = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> "작업이 한번 실행되고 결가가 반환";

        ScheduledFuture<String> future = ser.schedule(task, 3, TimeUnit.SECONDS);
        try {
            String s = future.get();
            System.out.println("s = " + s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        ser.shutdown();
    }
}
