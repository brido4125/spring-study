package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {
    /*
    * * hello.aop.order..*(..)) : hello.aop.order 패키지와 그 하위 패키지에 속한 모든 메서드를 지정
    * @Aroud 내의 String 값은 AspectJ 포인트컷 표현식이다
    * */
    @Around("execution(* hello.aop.order..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //join point 시그니처 -> String hello.aop.order.OrderRepository.save(String)처럼 호출될 target 메서드의 시그니처 정보
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();//실제 Target이 호출되는 로직
    }
}
