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
    //List -> Publisher가 보낼 data source
    Publisher<Integer> pub = iterPub(Stream.iterate(1, o -> o + 1).limit(10).collect(Collectors.toList()));
//    Publisher<String> mapPub = mapPub(pub, i -> "[" + i + "]");
    Publisher<Integer> sumPub = sumPub(pub);
    //Publisher<StringBuilder> sumPub = reducePub(pub, new StringBuilder() , (a, b) -> a.append(b + ","));
    sumPub.subscribe(logSub());
  }



  private static <T,R> Publisher<R> reducePub(Publisher<T> pub, R init, BiFunction<R, T, R> bf) {
    return new Publisher<>() {
      @Override
      public void subscribe(Subscriber<? super R> sub) {
        pub.subscribe(new DelegateSub<T, R>(sub) {
          R result = init;

          @Override
          public void onNext(T item) {
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
        pub.subscribe(new DelegateSub<Integer, Integer> (subscriber) {
          int sum = 0;
          @Override
          public void onNext(Integer integer) {
            sum += integer;
          }

          // 합계를 전부 계산하고 나서, subscriber로 데이터 넘기도록
          @Override
          public void onComplete() {
            subscriber.onNext(sum);
            subscriber.onComplete();
          }
        });
      }
    };
  }

  // T 타입이 들어와서 R 타입으로
  // Function Type의 f가 아래 Publisher
  private static <T, R> Publisher<R> mapPub(Publisher<T> pub, Function<T, R> f) {
    return new Publisher<R>() {
      @Override
      public void subscribe(Subscriber<? super R> subscriber) {
        pub.subscribe(new DelegateSub<T, R>(subscriber){
          @Override
          public void onNext(T item) {
            subscriber.onNext(f.apply(item));
          }
        });
      }
    };
  }

  private static <T> Subscriber<T> logSub() {
    return new Subscriber<T>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("PubSub.onSubscribe");
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(T item) {
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
          iter.forEach(subscriber::onNext); //한번에 Iter에 존재하는 데이터 전부 forEach 돌면서 전송
          subscriber.onComplete(); // 모든 Data를 보낸 경우, 반드시 호출
        } catch (RuntimeException runtimeException) {
          subscriber.onError(runtimeException);
        }
      }

      @Override
      public void cancel() {
        /**
         * Data를 받는 시간이 오래 걸릴 경우 cancel 호출 가능
         * Subscriber 쪽에서(인스턴스) 호출할 수 있음
         */
      }
    });
  }
}
