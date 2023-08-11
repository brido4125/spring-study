package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;



/*
* JDK Dynamic Proxy의 Handler와 동일한 기능을 하는 MethodInterceptor 구현
* */
@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target; // 호출할 실제 대상 객체

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("Method Name: " + method.getName());
        log.info("TimeProxy 실행");
        long start = System.currentTimeMillis();
        Object result = methodProxy.invoke(target, args);
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("Result Time: " + resultTime);
        return result;
    }
}
