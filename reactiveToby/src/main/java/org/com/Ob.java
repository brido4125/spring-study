package org.com;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public class Ob {

  // Duality (쌍대성)
  // Observer Pattern
  // Reactive Streams -> 표준 -> Java9에 API로 들어감

  public static void main(String[] args) {
    // Iterable 은 interface이고 Collection이 구현하고 있음
    // Iterable 을 구현한 경우, for - each 루프를 사용해서 순회 활 수 있음
    // Iterable <---> Observable , 서로 쌍대성 (동일한 기능이지만 반대의 방향으로 구현)
    // Iterable은 요소를 next()를 통해서 Pull 해오는 방식
    // Observable은 push 개념 -> data를 가져가라고 주는 방식

    Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);

    Iterable<Integer> iter = () -> new Iterator<>() {
      //원소를 모두 나열하지 않고 1 ~ 10 까지 순회하는 법
      int i = 0;
      final static int MAX = 10;
      @Override
      public boolean hasNext() {
        return i < MAX;
      }

      @Override
      public Integer next() {
        return i += 1;
      }
    };

    for (Integer i : iter) { //for-each
      System.out.println(i);
    }

    //java5 이전의 방식
    for (Iterator<Integer> it = iter.iterator(); it.hasNext(); ) {
      System.out.println(it.next());
    }

    // Source -> Event / Data -> Observer : Observer에 Observable이 데이터를 push
    // Observer / Observable
    Observer observer = new Observer() { // 데이터 받는 수신 쪽, 소비자
      // notifyObservers 할 경우 아래 Update 메서드 호출됨
      @Override
      public void update(Observable o, Object arg) {
        System.out.println("Observer.update");;
        System.out.println(arg);
      }
    };

    IntObservable intObservable = new IntObservable();
    intObservable.addObserver(observer); //event-driven에서 대표적으로 활용되는 패턴
    intObservable.run();
//    ExecutorService executorService = Executors.newSingleThreadExecutor();
//    executorService.execute(intObservable);

    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "EXIT");
//    executorService.shutdown();

    /**
     * 위에서 구현된 Observer 패턴의 문제점
     * 1. Complete에 해당하는 개념 및 처리가 없음
     * 2. 복구가능한 Error에 대한 처리
     */

    // 위 두가지 문제를 보완한 새로운 Observer 패턴이 Reactive Streams의 패러다임
  }

  // 생산자
  private static class IntObservable extends Observable implements Runnable {
    @Override
    public void run() {
      System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
      for (int i = 1; i <= 10; i++) {
        setChanged();
        // notifyObservers 내부 로직에 Observer의 update()를 호출하는 로직이 존재 -> 동일한 쓰레드에서 로직 수행됨
        notifyObservers(i); // push 하는 동작 iter.next()에 대응되는 로직
      }
    }
  }
}
