package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.MyInterface;

public class MyInterfaceProxy implements MyInterface {

    private final MyInterface target;

    public MyInterfaceProxy(MyInterface target) {
        this.target = target;
    }

    @Override
    public void call() {
        /**
         * do some infra logic
         */
        target.call();//invoke target biz logic
    }
}
