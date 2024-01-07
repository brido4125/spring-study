package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;//proxy 적용됨

    @Test
    void external() {
        /*
        * external() 내부에서 호출되는 internal() 로직은 AOP에 의해서 프록시를 거치치 않고 호출된다.
        * 즉, Advice가 적용되지 않는다.
        * -> Proxy 방식의 한계점이다.
        * */
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }

}