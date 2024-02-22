package operators;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Operators : 데이터를 가공할 수 있음
 * Publisher -> [Data1] -> Operator -> [Data2] -> Subscriber
 * 1. map (d1 -> f -> d2) : pub -> [Data1] -> mapPub -> [Data2] -> logSub 의 Flow를 가지도록
 */
public class PubSub {

  /**
   * Publisher : Data Stream을 계속 만들어 내는 역할 수행
   * Subscriber : 해당 Data Stream으로부터 Data를 받는 객체
   */
  public static void main(String[] args) {
    Publisher<Integer> pub = iterPub(Stream.iterate(1, o -> o += 1).limit(10).collect(Collectors.toList()));
//    Publisher<Integer> mapPub = mapPub(pub, i -> i * 10);
//    Publisher<Integer> sumPub = sumPub(pub);
    Publisher<Integer> sumPub = reducePub(pub, 0 , (a, b) -> a + b);
    sumPub.subscribe(logSub());
  }

  private static Publisher<Integer> reducePub(Publisher<Integer> pub, int init, BiFunction<Integer, Integer, Integer> bf) {
    return new Publisher<Integer>() {
      int result = init;
      @Override
      public void subscribe(Subscriber<? super Integer> sub) {
        pub.subscribe(new DelegateSub(sub) {
          @Override
          public void onNext(Integer item) {
            result = bf.apply(result, item);
          }

          @Override
          public void onComplete() {
            sub.onNext(result);
            sub.onComplete();
          }
        });
      }
    };
  }

  private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
    return new Publisher<Integer>() {

      //subscriber == logSub() 리턴값
      @Override
      public void subscribe(Subscriber<? super Integer> subscriber) {
        pub.subscribe(new DelegateSub(subscriber) {
          int sum = 0;
          @Override
          public void onNext(Integer integer) {
            sum += integer;
          }

          @Override
          public void onComplete() {
            subscriber.onNext(sum);
            subscriber.onComplete();
          }
        });
      }
    };
  }

  // 중간에 끼어드는 Publisher
  private static Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> f) {
    return new Publisher<Integer>() {
      @Override
      public void subscribe(Subscriber<? super Integer> subscriber) {
        pub.subscribe(new DelegateSub(subscriber) {
          @Override
          public void onNext(Integer integer) {
            subscriber.onNext(f.apply(integer));
          }
        });
      }
    };
  }

  private static Subscriber<Integer> logSub() {
    return new Subscriber<>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("PubSub.onSubscribe");
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer item) {
        System.out.println("onNext = " + item);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("throwable.getMessage() = " + throwable.getMessage());
      }

      @Override
      public void onComplete() {
        System.out.println("PubSub.onComplete");
      }
    };
  }

  private static Publisher<Integer> iterPub(final List<Integer> iter) {
    return subscriber -> subscriber.onSubscribe(new Subscription() {
      @Override
      public void request(long n) {
        try {
          iter.forEach(subscriber::onNext);
          subscriber.onComplete(); // 모든 Data를 보낸 경우 반드시 호출
        } catch (RuntimeException runtimeException) {
          subscriber.onError(runtimeException);
        }
      }

      @Override
      public void cancel() {
        /**
         * Data를 받는 시간이 오래 걸릴 경우 cancel 호출 가능
         * Subscriber 인스턴스에서 호출할 수 있음
         */
      }
    });
  }
}
