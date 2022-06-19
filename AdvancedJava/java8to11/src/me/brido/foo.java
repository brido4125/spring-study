package me.brido;

public class foo {
    public static void main(String[] args){

        /* //익명 내부 클래스 사용
      용 RunSomething runSomething = new RunSomething() {
            @Override
            public void doSomething() {
                System.out.println("not Lambda");
            }
        }*/
        //Lambda 형태로 표현
        RunSomething runSomething = () ->{
            System.out.println("Using Lambda");
            System.out.println("hello, Lambda");
        };

        runSomething.doSomething();


    }
}
