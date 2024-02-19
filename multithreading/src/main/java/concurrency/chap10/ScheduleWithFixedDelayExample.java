package concurrency.chap10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(5000);
        scheduledFuture.cancel(true);
        scheduledExecutorService.shutdown();

    }
}
