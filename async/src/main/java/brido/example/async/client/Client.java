package brido.example.async.client;

import brido.example.async.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    return integerFuture.get(); // blocking main thread
  }

  public void callCmplFutureMethod() {
    CompletableFuture<Integer> future = basicService.returnCmplFuture();
    System.out.println("non-blocking...");
    future.thenAccept(i -> System.out.println("callBack log result = " + i));
    System.out.println("Main Thread Do another things...");
  }
}
