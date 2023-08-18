package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect // annotation을 통해 advisor를 생성
public class LogTraceAspect {
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }


    /*
    * @Around 사용 : pointCut 설정
    * 메서드 바디 : advice 부분
    * excute의 내부 로직이 advice, 즉 추가하려는 부가 기능에 대한 로직이다.
    * */
    @Around("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 내부 로직이 advice의 내용을 가진다.
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);
            //로직 호출
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
