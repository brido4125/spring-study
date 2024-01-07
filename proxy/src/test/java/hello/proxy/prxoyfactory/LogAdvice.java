package hello.proxy.prxoyfactory;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@Slf4j
public class LogAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        String methodString  = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
        log.info("Invoked Method : {}", methodString);
        return invocation.proceed();
    }
}
