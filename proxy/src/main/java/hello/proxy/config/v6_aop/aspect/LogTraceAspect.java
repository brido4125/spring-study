package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/*
* AnnotationAwareAspectJAutoProxyCreator는 기본적으로 스프링 빈으로 등록되고,
* 사용자가 빈으로 등록한 Advisor를 찾아와서 프록시 객체를 적용시켜준다.
* 해당 AnnotationAwareAspectJAutoProxyCreator는
* @Aspect가 붙은 클래스의 어노테이션을 인식해서 해당 Aspect를 Advisor로 변환해서 저장해서 프록시 등록해준다.
* 결론적으로 Advisor기반 + @Aspect를 모두 처리해주는 빈 후처리기이다.
* */
@Slf4j
@Aspect //LogTraceAspect 객체가 Advisor 역할을 수행, annotation을 통해 advisor를 생성
public class LogTraceAspect {
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }


    /*
    * @Around 사용 : pointCut 설정
    * 메서드 바디 : advice 부분
    * excute의 내부 로직이 advice, 즉 추가하려는 부가 기능에 대한 로직이다.
    * Aspect가 PointCut + Advice인 Advisor의 역할을 수행한다.
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
