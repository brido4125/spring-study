package reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber<T> implements Subscriber<T> {
  private Subscription subscription;

  @Override
  public void onSubscribe(Subscription s) {
    System.out.println("MySubscriber.onSubscribe");
    this.subscription = s;
    s.request(2);
  }

  @Override
  public void onNext(T t) {
    System.out.println("OnNext item = " + t);
    subscription.request(2);
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
