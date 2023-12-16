package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        /*
        * Excute의 인자로 메세지와 콜백을 넘겨줌
        * */
        return template.excute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
