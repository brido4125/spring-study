package concurrency.chap03;

public class InterruptedExceptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Exception");
                System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
        System.out.println("thread.isInterrupted() = " + thread.isInterrupted());
    }



}
