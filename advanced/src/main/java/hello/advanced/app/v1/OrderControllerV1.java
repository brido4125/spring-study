package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* 로그 추적기 요구 사항 반영 - v1
* 요구 사항
* 1. 모든 Public 메서드의 호출과 응답 정보를 로그로 출력
* 2. 애플리케이션의 흐름을 변경하지 않음
* 3. 메서드 호출에 걸린 시간
* 4. 정상 흐름과 예외 흐름을 구분
* 5. 메서드 호출의 depth 표현
* 6. HTTP 요청을 구분 (Random id 부여)
* */
@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;

    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        } finally {
            trace.end(status);
        }
    }
}
