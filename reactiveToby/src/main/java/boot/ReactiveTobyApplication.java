package boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@SpringBootApplication
@EnableAsync
public class ReactiveTobyApplication {

  @Component
  public static class MyService {
    @Async(value = "threadPool")
    public ListenableFuture<String> hello() throws InterruptedException {
      log.info("hello()");
      Thread.sleep(2000);
      return new AsyncResult<>("Hello") ;
    }
  }

  @Bean
  ThreadPoolTaskExecutor threadPool() {
    ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
    te.setCorePoolSize(10); // 첫번째 요청이 들어오며 그때 core 숫자 만큼 스레드 생성
    te.setMaxPoolSize(100); // queue의 capacity가 전부 차 있는 경우 core -> max 사이즈로 스레드 수 늘림
    te.setThreadNamePrefix("myThread");
    te.initialize();
    return te;
  }

  public static void main(String[] args) {
    try (ConfigurableApplicationContext c = SpringApplication.run(ReactiveTobyApplication.class, args)) {

    }
  }

  @Autowired
  MyService myService;

  @Bean
  ApplicationRunner run() {
    return args -> {
      log.info("run()");
      ListenableFuture<String> hello = myService.hello();
      hello.addCallback(System.out::println, System.out::println);
      log.info("exit: " + hello.isDone());
//      log.info("result: " + hello.get());
    };
  }
}
