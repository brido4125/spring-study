package concurrency.chap03;

public class IsInterruptedExample {
  public static void main(String[] args) throws InterruptedException {

    Thread thread = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        System.out.println("ing...");
      }
      System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
    });

    thread.start();
    Thread.sleep(1000);
    thread.interrupt();
  }
}
