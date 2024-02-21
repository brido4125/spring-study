package org.com;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;
import java.util.concurrent.TimeUnit;

public class PubSub {
  public static void main(String[] args) throws InterruptedException {
    // Publisher <- Observable
    // Subscriber <- Observer
    Iterable<Integer> itr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    ExecutorService es = Executors.newSingleThreadExecutor();

    // Pub은 데이터를 주는 쪽, 누구에게 주는 지 정보를 알아야함
    Publisher<Integer> p = new Publisher<>() {
      @Override
      public void subscribe(Subscriber subscriber) {

        Iterator<Integer> it = itr.iterator();
        subscriber.onSubscribe(new Subscription() {
          // Publisher 주는 Event의 개수를 조절할 수 있음
          // BackPressure -> 생성하는 쪽의 속도는 천차만별임 ex) 디비 or 캐시만 하더라도 처리시간이 다름
          // 이러한 차이를 BackPressure를 통해서 조절하여 event / data를 subscribe 할 수 있도록 위한 기능
          @Override
          public void request(long n) {
            // lambda 내에서 n 변수에 대한 쓰기 연산 불가
            es.execute(() -> {
              int i = 0;
              try {
                while (i++ < n) {
                  if (it.hasNext()) {
                    subscriber.onNext(it.next());
                  } else {
                    subscriber.onComplete();
                    break;
                  }
                }
              } catch (RuntimeException e) {
                subscriber.onError(e);
              }
            });
          }

          @Override
          public void cancel() {

          }
        });
      }
    };

    Subscriber<Integer> s = new Subscriber<>() {

      Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("PubSub.onSubscribe");
        this.subscription = subscription;
        this.subscription.request(1);
      }

      // 발행된 Data / Evnent를 하나씩 가져옴
      @Override
      public void onNext(Integer item) {
        System.out.println("PubSub.onNext" + item);
        subscription.request(1);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("PubSub.onError" + throwable.getMessage());
      }

      @Override
      public void onComplete() {
        System.out.println("PubSub.onComplete");
      }
    };
    p.subscribe(s);
    boolean b = es.awaitTermination(1, TimeUnit.SECONDS);
    es.shutdown();
  }
}
