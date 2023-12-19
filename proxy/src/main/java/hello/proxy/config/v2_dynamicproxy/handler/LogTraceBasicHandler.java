package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/*
* Handler의 invoke 메서드에서 proxy가 수행할 로직을 구현
* */
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    /*
    * Proxy의 모든 method에 invoke 내부의 로직이 적용되는 문제가 존재함 -> Filter 사용해서 nolog() 메서드 처리
    * */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {
            // class.method() String 추출
            String methodString  = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(methodString);
            //로직 호출
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
