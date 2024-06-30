package concurrency.chap06;

import java.util.concurrent.Semaphore;

public class BinarySemaphore extends Semaphore {

  private int signal = 1;


  public BinarySemaphore() {
    super(1);
  }

  @Override
  public void acquire() {
    while (this.signal == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 설정
      }
    }
    this.signal = 0;
  }

  @Override
  public void release() {
    this.signal = 1;
    this.notify();
  }
}
