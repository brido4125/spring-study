package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {
    //PointCut 분리 가능, hello.aop.order와 아래 하위 패키지의 모든 메서드
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {} // -> 포인트컷 시그니처 = 메서드 이름 + 파라미터

    @Around("allOrder()") // 분리된 포인트컷을 입력해줌
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }
}
