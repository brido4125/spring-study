package brido.example.async.service;

import brido.example.async.result.FutureImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncService {

  @Async
  public void returnVoid() {
    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("BasicService.returnVoid");
  }

  @Async
  public Future<Integer> returnFuture() {
    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new FutureImpl<>(1500);
  }

  @Async
  public CompletableFuture<Integer> returnCmplFuture() {
    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return CompletableFuture.completedFuture(1700);
  }
}
