package generic;

import org.com.Ob;

import java.io.Closeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Generics {

  static class Hello<T extends Serializable & Comparable<T>> {
    // T -> type parameter
    // extends -> upper bound
    // multiple bound도 가능
  }



  public static void main(String[] args) {
    new Hello<String>();// type argument
    Integer[] integers = {1, 2, 3, 4, 5, 6, 7};
    countGreaterThan(integers, 4);

    int i = 10;
    Number n = i; // Number의 하위에 Integer, Float, Double 등이 상속받고 있음

    List<Integer> intList = new ArrayList<>();
//    List<Number> numbers = intList; -> compile error 발생

    List<Integer> integers1 = new ArrayList<>();

    List<Integer> list = Arrays.asList(1, 2, 3);
    method(list);
    //method2(list); // List<Integer>는 List<Object>의 서브 타입이 아니라서
  }

  // T : 타입을 모르지만, 추후에 정해지면 사용할 예정
  static <T> void print(T t) {
    System.out.println("t = " + t);
  }

  // ? : wildcards -> 타입을 모르고, 알 필요도 없다.
  static void method(List<?> t) {
    System.out.println("t = " + t);
  }

  // ? 와의 차이
  //
  static void method2(List<Object> objects) {
    System.out.println("objects = " + objects);
  }

  // T 타입의 generic에 제한을 걸어 주는 것
  static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
    return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0).count();
  }
}
