package scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FluxScEx {
  public static void main(String[] args) throws InterruptedException {
//    Flux.range(1, 10)
//            .publishOn(Schedulers.newSingle("pub"))
//            .log()
//            .subscribeOn(Schedulers.newSingle("sub"))
//            .subscribe(System.out::println);

    Flux.interval(Duration.ofMillis(500)) // DemonThread 사용
            .subscribe(s -> log.info("onNext: {}", s));
    TimeUnit.SECONDS.sleep(5);
    // user thread vs demon thread
    // JVM이 user thread가 하나라도 남겨져 있으면 프로세스 유지
    // JVM이 demon thread만 유지 중일 겨우 프로세스 종료

  }
}
