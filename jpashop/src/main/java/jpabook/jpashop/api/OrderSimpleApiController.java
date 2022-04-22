package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *
 * order 조회
 * order -> Member => XtoOne 관계
 * order -> Delivery => XtoOne 관계
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        for (Order order : orders) {
            order.getMember().getName();//order.getMember()까지는 Proxy 이후, getName에서 실체 객체 디비에서 가지고옴 => Lazy 강제 초기화
            order.getDelivery().getAddress();
        }
        return orders;
    }
}
