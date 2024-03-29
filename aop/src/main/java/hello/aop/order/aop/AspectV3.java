package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    /*
    * 어드바이스 여러개
    * */

    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {}


    //클래스 이름 패턴이 *Service인 클래 -> Tx는 주로 Service Layer에 적용되기 때문에
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    //hello.aop.order 패키지와 그 하위 패키지이면서 동시에 클래스 이름 끝이 Service로 끝나는 클래스의 모든 메서드를 지정

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }

    //hello.aop.order 패키지와 하위 패키지이면서 클래스 이름 패턴이 *Service인거
    @Around("allService() && allOrder()") //PointCut은 and, or ,not 연산자를 사용하여 조합이 가능하다.
    public Object doTx(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();//orderService.orderItem() 메서드 호출
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature()); }
    }
}
