package concurrency.chap09;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

  static int[] parallelSum = new int[2];

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int numThread = 2;

    CyclicBarrier barrier = new CyclicBarrier(numThread, new BarrierAction(parallelSum));

    for (int i = 0; i < numThread; i++) {
      new Thread(new Worker(i, numbers, barrier, parallelSum)).start();
    }
  }
}

class BarrierAction implements Runnable {
  private final int[] parallelSum;

  public BarrierAction(int[] parallelSum) {
    this.parallelSum = parallelSum;
  }

  @Override
  public void run() {
    int result = 0;
    for (int i : parallelSum) {
      result += i;
    }
    System.out.println("result = " + result);
  }
}

class Worker implements Runnable {

  private final int id;
  private final int[] numbers;
  private final CyclicBarrier cyclicBarrier;
  private final int[] parallelSum;

  public Worker(int id, int[] numbers, CyclicBarrier cyclicBarrier, int[] parallelSum) {
    this.id = id;
    this.numbers = numbers;
    this.cyclicBarrier = cyclicBarrier;
    this.parallelSum = parallelSum;
  }

  @Override
  public void run() {
    int start = id * (numbers.length / 2);
    int end = (id + 1) * (numbers.length / 2);
    int sum = 0;
    for (int i = start; i < end; i++) {
      sum += numbers[i];
    }
    parallelSum[id] = sum;

    try {
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (BrokenBarrierException e) {
      throw new RuntimeException(e);
    }
  }
}