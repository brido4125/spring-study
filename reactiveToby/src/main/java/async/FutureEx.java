package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureEx {

  // 비동기 작업의 결과 -> Future or Callback
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    // Future -> js promise,deferred 등등
    // 비동기 : 다른 스레드에 작업 할당
    ExecutorService es = Executors.newCachedThreadPool();


    Future<String> result = es.submit(() -> {
      Thread.sleep(2000);
      return "Hello";
    });


    System.out.println("Exit");
    System.out.println("result.get() = " + result.get()); // Blocking -> 기대하는 결과가 완료될때까지 대기
  }
}
