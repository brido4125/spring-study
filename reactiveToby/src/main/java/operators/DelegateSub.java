package operators;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

public class DelegateSub implements Subscriber<Integer> {

  private final Subscriber subscriber;

  public DelegateSub(Subscriber subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    subscriber.onSubscribe(subscription);
  }

  @Override
  public void onNext(Integer item) {
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
