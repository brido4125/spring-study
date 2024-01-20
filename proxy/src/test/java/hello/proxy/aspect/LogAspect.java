package hello.proxy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogAspect {
    @Around("execution(* hello.proxy.app..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log by proxy");
        return joinPoint.proceed();
    }
}
