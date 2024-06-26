package scheduler;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SchedulerExMulitThread {
  // Reactive Streams

  public static void main(String[] args) {
    Publisher<Integer> pub = sub -> {
      sub.onSubscribe(new Subscription() {
        @Override
        public void request(long n) {
          log.info("request()");
          sub.onNext(1);
          sub.onNext(2);
          sub.onNext(3);
          sub.onNext(4);
          sub.onNext(5);
          sub.onComplete();
        }

        @Override
        public void cancel() {

        }
      });
    };

//    Publisher<Integer> subOnPub = sub -> {
//      ExecutorService threadPool = Executors.newSingleThreadExecutor(new CustomizableThreadFactory() {
//        @Override
//        public String getThreadNamePrefix() {
//          return "subOn-";
//        }
//      });
//      threadPool.execute(() -> pub.subscribe(sub));
//    };

    Publisher<Integer> pupOnPub = sub -> {
      pub.subscribe(new Subscriber<Integer>() {
        ExecutorService es = Executors.newSingleThreadExecutor(new CustomizableThreadFactory(){
          @Override
          public String getThreadNamePrefix() {
            return "pubOn-";
          }
        });

        @Override
        public void onSubscribe(Subscription s) {
          sub.onSubscribe(s);
        }

        @Override
        public void onNext(Integer integer) {
          es.execute(() -> sub.onNext(integer));
        }

        @Override
        public void onError(Throwable t) {
          es.execute(() -> sub.onError(t));
          es.shutdown();
        }

        @Override
        public void onComplete() {
          es.execute(() -> sub.onComplete());
          es.shutdown();
        }
      });
    };

    pupOnPub.subscribe(new Subscriber<Integer>() {
      @Override
      public void onSubscribe(Subscription s) {
        log.info("onSubscribe");
        s.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer integer) {
        log.info("onNext : {}", integer);
      }

      @Override
      public void onError(Throwable t) {
        log.info("onError : {}", t);
      }

      @Override
      public void onComplete() {
        log.info("onComplete");
      }
    });
    System.out.println("exit");
  }
}
