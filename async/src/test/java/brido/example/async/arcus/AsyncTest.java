package brido.example.async.arcus;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ArcusClientPool;
import net.spy.memcached.ConnectionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncTest {
  @Test
  public void before() throws InterruptedException {
    ArcusClientPool clientPool = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 2);

    CompletableFuture<Boolean> cf = CompletableFuture.supplyAsync(() -> {
      Boolean result = null;
      try {
        result = clientPool.set("testKey", 3, "value").get();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        Thread.currentThread().interrupt();
      }
      return result;
    });

    //callback 처리
    cf.thenAcceptAsync(result -> {
      System.out.println("result = " + result);
    }).exceptionallyAsync(t -> {
      System.out.println("t.getCause() = " + t.getCause());
      return null;
    });

    System.out.println("main thread done.");;
    Thread.sleep(300);// prevent interrupt to common pool.
  }

  @Test
  public void after() throws InterruptedException {
    ArcusClientPool clientPool = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 2);

    /*
    clientPool.set("testKey", 3, "value")
            .thenAcceptAsync(result -> {
              System.out.println("result = " + result);
            }).exceptionallyAsync(t -> {
              System.out.println("t.getCause() = " + t.getCause());
              return null;
            });
    */

    System.out.println("main thread done.");
    Thread.sleep(300);// prevent interrupt to common pool.
  }

}
