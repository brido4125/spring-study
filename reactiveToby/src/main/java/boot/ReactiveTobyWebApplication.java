package boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Slf4j
@SpringBootApplication
@EnableAsync
public class ReactiveTobyWebApplication {

  @RestController
  public static class MyController {

    Queue<DeferredResult<String>> results = new ConcurrentLinkedDeque<>();

    @GetMapping("/dr")
    public DeferredResult<String> callable() {
      log.info("dr");
      DeferredResult<String> dr = new DeferredResult<>(60000L);
      results.add(dr);
      return dr;
    }

    @GetMapping("/dr/count")
    public String drCount() {
      return String.valueOf(results.size());
    }

    @GetMapping("/dr/event")
    public String drEvent(String msg) {
      for (DeferredResult<String> dr : results) {
        dr.setResult("Hello " + msg);
        results.remove(dr);
      }
      return "OK";
    }
//    public Callable<String> callable() {
//      log.info("callable");
//      return () -> {
//        log.info("async");
//        Thread.sleep(2000);
//        return "hello";
//        };
//    }
//    public String callable() throws InterruptedException {
//      log.info("callable");
//      Thread.sleep(2000);
//      return "hello";
//    }
  }

  public static void main(String[] args) {
    SpringApplication.run(ReactiveTobyWebApplication.class, args);
  }
}
