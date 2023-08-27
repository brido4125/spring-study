package hello.aop.exam.aop;


import hello.aop.exam.ExamService;
import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class RetryAspect {

    @Pointcut("@annotation(retry)")
    public void retryPointCut(Retry retry) {
    }

    @Around("retryPointCut(retry)")
    public Object retryAdvice(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[Retry] {} [args] = {}", joinPoint.getSignature(), retry);
        int maxRetry = retry.value();
        Exception exceptionHolder = null;
        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }

}
