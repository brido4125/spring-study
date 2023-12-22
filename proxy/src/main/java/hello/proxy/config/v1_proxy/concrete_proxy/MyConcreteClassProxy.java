package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.MyConCreteClass;

public class MyConcreteClassProxy extends MyConCreteClass {
    private final MyConCreteClass target;

    public MyConcreteClassProxy(MyConCreteClass myConCreteClass) {
        super(null);
        this.target = myConCreteClass;
    }

    @Override
    public void call() {
        /**
         * do Some infra logic
         */
        target.call(); // invoke target biz logic
    }
}
