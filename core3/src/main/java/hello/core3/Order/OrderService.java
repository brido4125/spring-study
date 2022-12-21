package hello.core3.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
