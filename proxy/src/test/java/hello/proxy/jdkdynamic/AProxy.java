package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.A;
import hello.proxy.jdkdynamic.code.AImpl;

public class AProxy implements A {

    private final AImpl target;

    public AProxy(AImpl target) {
        this.target = target;
    }

    @Override
    public String call() {
        long start = System.currentTimeMillis();
        String result = target.call();
        long end = System.currentTimeMillis() - start;
        System.out.println("end = " + end);
        return result;
    }
}
