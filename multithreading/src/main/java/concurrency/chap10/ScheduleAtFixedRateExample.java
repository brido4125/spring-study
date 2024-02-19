package concurrency.chap10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        /**
         * 수행중인 작업이 끝나지 않고 period가 도달하더라도 작업을 종료하지 않고 종료될 때 까지 기다렸다가 다음 작업을 실행
         * period를 Runnable 또는 Callable의 수행 시간보다 길게 잡아야 예상한대로 동작
         */
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(5000);
        scheduledFuture.cancel(true);
        scheduledExecutorService.shutdown();

    }
}
