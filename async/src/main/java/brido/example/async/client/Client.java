package brido.example.async.client;

import brido.example.async.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class Client {

  private final AsyncService basicService;

  @Autowired
  public Client(AsyncService basicService) {
    this.basicService = basicService;
  }

  public void callVoidMethod() {
    basicService.returnVoid();
  }

  public Integer callFutureMethod() throws ExecutionException, InterruptedException {
    Future<Integer> integerFuture = basicService.returnFuture();
    System.out.println("Thread.currentThread().getName() in 호출한곳 = " + Thread.currentThread().getName());
    return integerFuture.get(); // blocking main thread
  }

  //Exception 발생
  public void callInteger() {
    Integer result = basicService.returnValue();
    System.out.println("result thread = " + Thread.currentThread().getName());
  }

  public void callCmplFutureMethod() {
    CompletableFuture<Integer> future = basicService.returnCmplFuture();
    System.out.println("non-blocking...");
    future.thenAccept(i -> System.out.println("callBack log result = " + i));
    System.out.println("Main Thread Do another things...");
  }

  @SuppressWarnings("deprecation")
  public void listenalbeFuture() {
    ListenableFuture<Boolean> lf = basicService.returnListenableFuture();
    lf.addCallback(r -> System.out.println("r = " + r), t -> System.out.println("t = " + t));
    System.out.println("Main Thread do another...");
  }

  @SuppressWarnings("deprecation")
  public void completableFuture() {
    CompletableFuture<Boolean> cf = basicService.completableFuture();

    cf.thenAcceptAsync(r -> System.out.println("r = " + r))
            .exceptionallyAsync(t -> {System.out.println("t = " + t);
              return null;
    });
    System.out.println("Main Thread do another...");
  }
}
