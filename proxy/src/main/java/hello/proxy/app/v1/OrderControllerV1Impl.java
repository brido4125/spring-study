package hello.proxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1 {

    private final OrderServiceV1 orderService;//인터페이스에 의존 -> 프록시로 바꿔치기 가능

    public OrderControllerV1Impl(OrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
