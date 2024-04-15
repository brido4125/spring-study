package generic;

import org.com.Ob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Generics2 {

  /*
  * 아래 둘의 차이?
  * */

  static <T> void method1(List<T> list) {
    // 메소드 내부에서 T 타입으로 변수 정의 가능
  }

  static void method2(List<?> list) {
    // 매소드 내부에서 ? 타입을 사용할 수 없음
    list.add(null); // null만 삽입할 수 있음
    list.size(); list.clear(); // 등의 원소와 상관없는 메서드 호출 가능
  }

  static void method3(List<Object> list) {

  }

  static void method4(List<? extends Object> list) {

  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    // 둘 다 complie 가능
    method1(list);
    method2(list);
    // List<Object>는 불가 -> List<Integer>는 List<Object>의 서브 타입이 아니라서
    //  method3(list);
    method4(list);

    // wildcard capture
  }

  static <T> void reverse1(List<T> list) {
    List<T> temp = new ArrayList<>(list);
    for (int i = 0; i < list.size(); i++) {
      list.set(i, temp.get(list.size() -i - 1));
    }
  }

  // capture error 발생 -> reverseHelper로 해결
  // wild card의 경우 구체적인 타입의 정보를 얻어오지 못하기 때문에 capture 에러 발생
  static void reverse2(List<?> list) {
    reverseHelper(list);
  }

  private static <T> void reverseHelper(List<T> list) {
    List<T> temp = new ArrayList<>(list);
    for (int i = 0; i < list.size(); i++) {
      list.set(i, temp.get(list.size() -i - 1));
    }
  }

  //isEmpty2로 대체 가능함. 즉, 제네릭 사용하지 않아도 됨
  static <T> boolean isEmpty1(List<T> list) {
    return list.size() == 0;
  }

  // wildcard를 사용하는 경우는 해당 타입을 알지 못해도 될 때 사용
  static boolean isEmpty2(List<?> list) {
    return list.size() == 0;
  }

  static <T> long frequecy1(List<T> list, T elem) {
    return list.stream().filter(e -> e.equals(elem)).count();
  }

  //isEmpty2 와 frequecy2가 공식문서에서 제안하는 메서드 구현
  //위 두 메서드는 내부 구현에서 T 타입을 사용하지 않아도 되기 때문에
  static long frequecy2(List<?> list, Object elem) {
    // eqauls는 Object 클래스에 정의된 메서드라서 사용 가능
    return list.stream().filter(e -> e.equals(elem)).count();
  }

  static <T extends Comparable<T>> T max1(List<T> list) {
    return list.stream().reduce((e1, e2) -> e1.compareTo(e2) > 0 ? e1 : e2).get();
  }

  //Collections.max가 아래와 같이 구현됨 -> 이해 제대로 안됨
  static <T extends Comparable<? super T>> T max2(List<? extends T> list) {
    return list.stream().reduce((e1, e2) -> e1.compareTo(e2) > 0 ? e1 : e2).get();
  }
}
