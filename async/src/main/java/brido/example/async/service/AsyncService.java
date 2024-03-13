package brido.example.async.service;

import brido.example.async.result.FutureImpl;
import net.spy.memcached.ArcusClient;
import net.spy.memcached.ArcusClientPool;
import net.spy.memcached.ConnectionFactoryBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
  public Integer returnValue() {
    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new FutureImpl<>(1500).get();
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

  // spring 5까지는 사용 가능
  @SuppressWarnings("deprecation")
  @Async
  public ListenableFuture<Boolean> returnListenableFuture() {
    ArcusClientPool clientPool = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 2);
    return new ListenableFutureTask<>(() -> clientPool.set("test", 3, "value").get());
  }

  @Async
  public CompletableFuture<Boolean> completableFuture() {
    final ArcusClientPool clientPool = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 2);

    return CompletableFuture.supplyAsync(() -> {
      Boolean result = null;
      try {
        result = clientPool.set("test", 3, "value").get();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }
      return result;
    });
  }

  /*
  @Async
  public CompletableFuture<Boolean> upgrade() {
    final ArcusClientPool clientPool = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 2);
    return clientPool.set("test", 3, "value");
  }
  */
}
