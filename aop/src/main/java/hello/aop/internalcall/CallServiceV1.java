package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class CallServiceV1 {

    // 생성자로 주입 시 순환 참조 문제 발생
    private CallServiceV1 callServiceV1;// 자기 자신을 내부 Field로 가짐

    // setter 주입 사용
    // setter 주입으로 container에 등록된 proxy 인스턴스를 주입시켜준다.
    // setter 주입도 자동으로 해준다  -> 스프링 컨테이너 로딩 시점에 세터 호출해서 자동으로 주입
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal();// 등록된 Proxy의 internal 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
