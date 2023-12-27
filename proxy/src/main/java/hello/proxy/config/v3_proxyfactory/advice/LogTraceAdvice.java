package hello.proxy.config.v3_proxyfactory.advice;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;


/*
* Advice : 프록시에 적용하는 부가 기능 로직
* CGLIB가 사용하는 MethodInterceptor와는 다른 패키지
* */
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace logTrace;

    public LogTraceAdvice(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            Method method = invocation.getMethod();
            String methodString  = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(methodString);
            //타겟의 로직 호출
            Object result = invocation.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
