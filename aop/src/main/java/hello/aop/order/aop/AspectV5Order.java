package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;


/*
* Advice 순서 지정 -> 클래스 단위로 @Order 지정
* */
@Slf4j
public class AspectV5Order {

    //@Around가 붙은 advice를 @Aspect가 붙은 inner 클래스화 -> 하나의 Inner Class가 하나의 advisor

    //순서 지정을 위해서 내부 클래스 생성
    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
            return joinPoint.proceed();
        }
    }

    //순서 지정을 위해서 내부 클래스 생성
    @Aspect
    @Order(1) // 숫자가 낮을수록 우선순위가 높음
    public static class TxAspect {
        //hello.aop.order 패키지와 그 하위 패키지이면서 동시에 클래스 이름 끝이 Service로 끝나는 클래스의 모든 메서드를 지정
        @Around("Pointcuts.orderAndService()") //PointCut은 and, or ,not 연산자를 사용하여 조합이 가능하다.
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

}
