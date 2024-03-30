package reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

public class ListPublisher<T> implements Publisher<T> {

  private final List<T> list;

  public ListPublisher(List<T> list) {
    this.list = list;
  }

  @Override
  public void subscribe(Subscriber<? super T> s) {
    s.onSubscribe(new Subscription() {
      @Override
      public void request(long n) {
        try {
          list.forEach(s::onNext);
          s.onComplete();
        } catch (RuntimeException e) {
          s.onError(e);
        }

      }

      @Override
      public void cancel() {

      }
    });
  }
}
