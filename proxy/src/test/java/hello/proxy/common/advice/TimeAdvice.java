package hello.proxy.common.advice;


import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/*
* JDK 동적 proxy의 InvocationHandler와 CGLIB이 제공하는 MethodInterceptor
* 의 역할을 수행하는 클래스, 인프라 로직이 구현되는 곳
* */
@Slf4j
public class TimeAdvice implements MethodInterceptor {

    // Target을 field 값으로 가지지 않아도 된다.
    // target 정보는 invocation parameter 안에 들어가 있다.

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long start = System.currentTimeMillis();
        Object result = invocation.proceed(); //target이 호출되는 메서드 호출
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("Result Time: " + resultTime);
        return result;
    }
}
