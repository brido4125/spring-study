package reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SumPublisher<T> implements Publisher<T> {

  private final Publisher<T> publisher;

  public SumPublisher(Publisher<T> publisher) {
    this.publisher = publisher;
  }

  @Override
  public void subscribe(Subscriber<? super T> s) {
    publisher.subscribe(new DelegateSubscriber<T>(s));
  }

  private static class DelegateSubscriber<T> implements Subscriber<T> {

    private final Subscriber<T> subscriber;

    public DelegateSubscriber(Subscriber<T> subscriber) {
      this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Subscription s) {
      subscriber.onSubscribe(s);
    }

    @Override
    public void onNext(T t) {
      subscriber.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
      subscriber.onError(t);
    }

    @Override
    public void onComplete() {
      subscriber.onComplete();
    }
  }
}
