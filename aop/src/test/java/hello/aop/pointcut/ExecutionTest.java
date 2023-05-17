package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        //Reflection 단계에서 Method 정보를 가져온다.
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        //출력 값 : public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod: {}", helloMethod);
    }


    /* 가장 정확하게 (specific) 하게 매칭하기*/
    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /* 가장 많이 생략한 execution*/
    /* 3가지만 넣으면 됨
    * 1. 반환 타입
    * 2. 메서드 이름
    * 3. 인자 -> .. -> 파라미터의 타입과 수가 상관없다는 설정
    * */
    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * Pattern 매칭
    * */
    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* he*(..)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *el(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFail() {
        pointcut.setExpression("execution(* no(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch1() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(* hello.aop.member.*.* (..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactFalse() {
        pointcut.setExpression("execution(* hello.aop.*.* (..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactSubPackage() {
        pointcut.setExpression("execution(* hello.aop..*.* (..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 부모 타입으로도 타입 매칭 가능
    * */
    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 자식 타입에만 존재하는 메서드는 부모 타입으로 타입 매칭 불가능
    * 여기서는 internal 메서드
    * */
    @Test
    void typeMatchInternalNoSuperMatch() throws NoSuchMethodException{
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException{
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);
        Assertions.assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isTrue();
    }


    //String 타입의 args를 허용
    @Test
    void argsMatching() {
        pointcut.setExpression("execution(* *(String))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchingNoArgs() {
        pointcut.setExpression("execution(* *())");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }


    /*
    * 정확하게 한개의 파라미터만 허용
    * */
    @Test
    void argsMatchingOneArgs() {
        pointcut.setExpression("execution(* *(*))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 숫자와 상관없이 모든 파라미터 허용
    * */
    @Test
    void argsMatchingAll() {
        pointcut.setExpression("execution(* *(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /*
    * String으로 시작하고 숫자와 무관하게 모든 파라미터 허용
    * */
    @Test
    void argsMatchingComplex() {
        pointcut.setExpression("execution(* *(String,..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }



}
