package generic;

import java.io.Serializable;
import java.util.function.Function;

public class IntersectionType {
  public static void main(String[] args) {
    // lambda를 사용해도 내부적으로 넘어갈 때는 클래스가 생성되서 익명 클래스처럼 사용된다.
    // LambdaMetaFactory
    hello((Function & Serializable & Cloneable) s -> s);
  }

  // marker interface가 Intersection에 적용될 수 있음
  // 구현해야할 메서드 개수가 0개이기 때문에

  private static <T extends Function & Serializable & Cloneable> void hello(T function) {

  }
}
