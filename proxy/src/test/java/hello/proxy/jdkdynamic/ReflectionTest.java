package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();
        //공통 로직 1 시작
        log.info("start");
        String result1 = target.callA(); //호출하는 메서드가 다름
        log.info("result1: {}", result1);
        //공통 로직 1 끝

        //공통 로직 2 시작
        log.info("start");
        String result2 = target.callB(); //호출하는 메서드가 다름
        log.info("result2: {}", result2);
        //공통 로직 2 끝
    }


    /*
    * Reflection : 클래스나 메서드의 메타정보를 사용해서 동적으로 호출하는 메서드를 변경 할 수 있음
    * */
    @Test
    void reflection1() throws Exception {
        //클래스 정보
        //내부 클래스는 구분을 위해 $를 사용함
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        //callA 메서드 정보
        Method callA = classHello.getMethod("callA"); // 메서드 메타 정보 획득
        Object result1 = callA.invoke(target); // 실제 메서드 호출
        log.info("result1: {}", result1);

        //callB 메서드 정보
        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result2: {}", result2);
    }

    @Test
    void reflection2() throws Exception {
        //클래스 meta 정보 획득
        //내부 클래스는 구분을 위해 $를 사용함
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        /*
        * 공통적으로 사용되는 로직은 dynamicCall로 메서드화 시킬 수 있음
        * classHello.getMethod("잘못된함수명") -> 컴파일 타임에 해당 에러 잡아낼 수 없음, RuntimeException 발생
        * */

        //callA 메서드 정보
        Method callA = classHello.getMethod("callA");
        dynamicCall(callA, target);

        //callB 메서드 정보
        Method callB = classHello.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result: {}", result);
    }

    @Slf4j
    static class Hello{
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
