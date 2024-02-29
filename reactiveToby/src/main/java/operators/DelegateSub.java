package operators;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

public class DelegateSub<T, R> implements Subscriber<T> {

  private final Subscriber subscriber;

  public DelegateSub(Subscriber<? super R> subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    subscriber.onSubscribe(subscription);
  }

  @Override
  public void onNext(T item) {
    subscriber.onNext(item);
  }

  @Override
  public void onError(Throwable throwable) {
    subscriber.onError(throwable);
  }

  @Override
  public void onComplete() {
    subscriber.onComplete();
  }
}
