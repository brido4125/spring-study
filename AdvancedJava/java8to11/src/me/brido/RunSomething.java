package me.brido;

@FunctionalInterface  //FunctionalInterface must have only one abstract method.
public interface RunSomething {
    void doSomething();
    //void doNoting(); 추상화 메서드가 여러개 있으면 함수형 인터페이스가 아니다.
    static void printName(){
        System.out.println("brido");
    }//static method 정의
    default void printAge(){
        System.out.println("24");
    }// default method 정의
}
