package hello.proxy.app;

public class MyConCreteClass {

    private final Object object;

    public MyConCreteClass(Object object) {
        this.object = object;
    }

    public void call() {
        System.out.println("MyConCreteClass.call");
    }
}
