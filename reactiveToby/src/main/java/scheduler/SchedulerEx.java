package scheduler;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class SchedulerEx {
  // Reactive Streams

  public static void main(String[] args) {
    Publisher<Integer> pub = sub -> {
      sub.onSubscribe(new Subscription() {
        @Override
        public void request(long n) {
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

    pub.subscribe(new Subscriber<Integer>() {
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
  }


}
