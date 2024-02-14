package concurrency.chap10;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleRunnableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService ser = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("ScheduleRunnableExample.run");

        ScheduledFuture<?> schedule = ser.schedule(task, 3, TimeUnit.SECONDS);
        Object o = schedule.get();
        System.out.println("o = " + o);
        Thread.sleep(4000);
        ser.shutdown();
    }
}
