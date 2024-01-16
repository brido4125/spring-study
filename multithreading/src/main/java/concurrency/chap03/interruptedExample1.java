package concurrency.chap03;

public class interruptedExample1 {
  public static void main(String[] args) throws InterruptedException {

    Thread thread = new Thread(() -> {
      while (!Thread.interrupted()) {
        System.out.println("ing...");
      }
      //false -> true -> false 순으로 인터럽트 상태 변화
      System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
    });

    thread.start();
    Thread.sleep(1000);
    thread.interrupt();
  }
}
