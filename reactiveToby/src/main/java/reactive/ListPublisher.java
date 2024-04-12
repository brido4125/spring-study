package reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.List;

public class ListPublisher<T> implements Publisher<T> {

  private final Iterator<T> list;

  public ListPublisher(List<T> list) {
    this.list = list.iterator();
  }

  @Override
  public void subscribe(Subscriber<? super T> s) {
    s.onSubscribe(new Subscription() {


      @Override
      public void request(long n) {
        int count = 0;
        try {
          while (count++ < n) {
            if (list.hasNext()) {
              s.onNext(list.next());
            } else {
              s.onComplete();
            }
          }
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
