package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class TraceAspect {

    //@Trace 어노테이션이 존재하면, 해당 aspect가 걸림
    @Pointcut("@annotation(hello.aop.exam.annotation.Trace)")
    public void tracePointCut() {}

    @Before("tracePointCut()")
    public void doTrace(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("[Trace] {}, [args] {}", joinPoint.getSignature(), args);
    }
}
