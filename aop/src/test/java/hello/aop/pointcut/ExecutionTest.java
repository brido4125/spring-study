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
    Method helloMethod; //메서드 메타정보

    /*
    * execution(접근제어자 반환타입 선언타입메서드이름(파라미터) 예외)
    * */

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
    //service의 hello() 메서드만 정확하게 매칭
    // 파라미터는 패키지 생략 가능
    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /* 가장 많이 생략한 execution*/
    /* 3가지만 넣으면 됨
    * 1. 반환 타입 : * 아무거나 가능
    * 2. 메서드 이름 : * 아무 메서드
    * 3. 인자 -> .. -> 파라미터의 타입과 수가 상관없다는 설정
    * */
    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 메서드 이름으로 매칭
    * */
    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 메서드 이름을 Pattern 매칭
    * hello -> he*를 통해서 매칭
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
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(* hello.aop.member.*.* (..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * hello.aop.* -> 정확인 aop 패키지 내의 클래스만 대상, 하위 패키지는 포함되지 않아 오류 발생
    * */
    @Test
    void packageExactFalse() {
        pointcut.setExpression("execution(* hello.aop.*.* (..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    /*
    * hello.aop..* -> aop 패키지의 하위 패키지까지 포함됨
    * */
    @Test
    void packageExactSubPackage1() {
        pointcut.setExpression("execution(* hello.aop..*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 부모 타입으로도 타입 매칭 가능
    * 상위 타입을 선언해도 해당 자식 타입은 매칭된다.
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
        //자식 클래스만 가지는 Internal 메서드 정보
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
    * 정확하게 한개의 파라미터만 허용, 모든 타입 허용
    * */
    @Test
    void argsMatchingOneArgs() {
        pointcut.setExpression("execution(* *(*))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    /*
    * 숫자와 상관없이 모든 파라미터 허용, 모든 타입을 허용 -> (..) 사용
    * */
    @Test
    void argsMatchingAll() {
        pointcut.setExpression("execution(* *(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /*
    * String으로 시작하고 숫자와 무관하게 모든 파라미터 허용
    * (String) , (String, Xxx), (String, Xxx, Xxx)
    * */
    @Test
    void argsMatchingComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
