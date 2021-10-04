package hellojpa;

public class ValueMain {
    public static void main(String[] args) {
        int a = 10;
        int b = a;

        a= 20;

        //java의 기본 타입은 절대 공유되지 않는다.
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
