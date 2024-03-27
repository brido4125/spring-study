package reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber<T> implements Subscriber<T> {
  //Publisher 내에서 만든 Subscription 인스턴스 내의 메서드 호출
  @Override
  public void onSubscribe(Subscription s) {
    s.request(Integer.MAX_VALUE);
  }

  @Override
  public void onNext(T t) {
    System.out.println("OnNext item = " + t);
  }

  @Override
  public void onError(Throwable t) {
    System.out.println("t.getCause() = " + t.getCause());
  }

  @Override
  public void onComplete() {
    System.out.println("MySubscriber.onComplete");
  }
}
