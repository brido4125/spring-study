package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


/*
* @Around : 메서드 호출 전후에 수행, 쉽게 말해 proxy로 메서드의 모든 것을 조작 가능
* ProceedingJoinPoint : 다음에 호츌될 다른 advice나 target을 호출할 수 있음
* proceed()는 여러번 호출 가능함 -> retry 가능
* */

@Slf4j
@Aspect
public class AspectV6Advice {
    //@Around는 아래의 모든 구간에 Proxy의 기능을 weaving할 수 있음
    //나머지 4개의 어노테이션은 @Around의 범위를 세분화 시킨 범위에서 위빙 하도록 해준다.
    @Around("Pointcuts.orderAndService()") //PointCut은 and, or ,not 연산자를 사용하여 조합이 가능하다.
    public Object doTx(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            //@Before의 로직이 실행되는 구간
            Object result = joinPoint.proceed();//orderService.orderItem() 메서드 호출
            //@AfterReturning의 로직이 실행되는 로직
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing의 로직이 실행되는 구간
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After의 로직이 실행되는 구간
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    /*
    * joinPoint.proceed를 호출하는 로직 없음 -> 딱 그전까지만 동작할 로직 작성
    * */
    @Before("Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }


    /*
    * returning의 Type 체크를 잘해야함
    * 지금 경우 실행되는 함수의 반환형이 void이기 때문에 Object로 해야 result 값을 받을 수 있다
    * void인 경우 Object result에 null 값 설정
    * */
    @AfterReturning(value = "Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[Result] {}", result);//@AfterReturning은 리턴값을 수정할 수 없다. -> 읽기만 가능
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
