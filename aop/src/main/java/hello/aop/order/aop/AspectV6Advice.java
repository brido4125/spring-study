package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
    @Around("Pointcuts.orderAndService()") //PointCut은 and, or ,not 연산자를 사용하여 조합이 가능하다.
    public Object doTx(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            //@Before
            Object result = joinPoint.proceed();//orderService.orderItem() 메서드 호출
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }


    /*
    * returning의 Type 체크를 잘해야함
    * 지금 경우 실행되는 함수의 반환형이 void이기 때문에 Object로 해야 result 값을 받을 수 있다
    * */
    @AfterReturning(value = "Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[Result] {}", result);//@AfterReturning은 리턴값을 수정할 수 없다.
        log.info("[AfterReturning] {}", joinPoint.getSignature());
    }

    @AfterThrowing(value = "Pointcuts.orderAndService()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("[AfterThrowing] {}", joinPoint.getSignature());
        log.info("[AfterThrowing] exception", exception);
    }

    @After("Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
