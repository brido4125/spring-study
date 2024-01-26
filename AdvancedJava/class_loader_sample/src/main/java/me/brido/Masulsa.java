package me.brido;

public class Masulsa {

  // 해당 Jar의 클래스파일 자체를 변경하지는 않는다.
  // 클래스 로더가 생성된 클래스 파일을 메모리로 읽어올 때 javaAgent를 거쳐서 변경된 바이트 코드로 읽어들여서 사용한다.
  // Transparent한 방식
  public static void main(String[] args) {
    System.out.println(new Moja().pullOut());
  }
}
