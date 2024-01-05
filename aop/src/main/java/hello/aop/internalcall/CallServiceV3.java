package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;


// 구조를 변경 (분리)
@Slf4j
@Component
public class CallServiceV3 {

    private final InternalService internalService;

    // 내부에서 호출되는 메서드를 가진 클래스를 외부로부터 주입받아서 호출
    // 해당 클래스에도 advice 적용되도록 포인트컷 설정 필요
    public CallServiceV3(InternalService internalService) {
        this.internalService = internalService;
    }

    public void external() {
        log.info("call external");
        internalService.internal();
    }


}
