package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
public class Toby009Application {

  @RestController
  public static class MyController {

    @Autowired
    private MyService myService;

    RestTemplate rt = new RestTemplate();

    @GetMapping("/rest")
    public ListenableFuture<String> rest(int idx) throws InterruptedException {
//      String forObject
//              = rt.getForObject("http://localhost:8081/service?req={req}", String.class, "hello" + idx);
      return myService.work("hello" + idx);
    }
  }

  @Service
  public static class MyService {

    @Async
    public ListenableFuture<String> work(String req) throws InterruptedException {
      System.out.println("==================");
      Thread.sleep(2000);
      return new AsyncResult<>(req + "asyncwork");
    }
  }

  @Bean
  public ThreadPoolTaskExecutor myThreadPool() {
    ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
    te.setCorePoolSize(1);
    te.setMaxPoolSize(1);
    te.initialize();
    return te;
  }

  public static void main(String[] args) {
    SpringApplication.run(Toby009Application.class, args);
  }
}
