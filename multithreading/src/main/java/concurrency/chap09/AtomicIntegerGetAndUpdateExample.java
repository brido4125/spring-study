package concurrency.chap09;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerGetAndUpdateExample {
  private static AtomicInteger balance = new AtomicInteger(1000);
  public static void main(String[] args) throws InterruptedException {


    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        int withdrawalAmount = 500;
        int updateBalance = balance.getAndUpdate(balance -> {
          if (balance >= withdrawalAmount) {
            return balance - withdrawalAmount;
          } else {
            return balance;
          }
        });

        if (updateBalance < 0) {
          System.out.println("실패");
        } else {
          System.out.println(updateBalance);
        }
      }).start();
    }

  }
}
