package generic;

public class Generics {

  static class Hello<T> {
    // T -> type parameter
  }

  public static void main(String[] args) {
    new Hello<String>();// type argument


  }
}
