package reactive;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Double> lists = List.of(1.0, 1.1, 1.2, .13);
    ListPublisher<Double> publisher = new ListPublisher<>(lists);
    publisher.subscribe(new MySubscriber<>());
  }
}
