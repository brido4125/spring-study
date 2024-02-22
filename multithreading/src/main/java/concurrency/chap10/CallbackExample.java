package concurrency.chap10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {

    interface Callback {
        void onComplete(int result);
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int result = 42;

            Callback callback = new CallbackImpl();
            callback.onComplete(result);

        });
    }

    static class CallbackImpl implements Callback {
        @Override
        public void onComplete(int result) {
            System.out.println("result = " + result);
        }
    }
}
