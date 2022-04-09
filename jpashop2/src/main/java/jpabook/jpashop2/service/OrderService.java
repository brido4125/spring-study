package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.Delivery;
import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.domain.Order;
import jpabook.jpashop2.domain.OrderItem;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.ItemRepository;
import jpabook.jpashop2.repository.MemberRepository;
import jpabook.jpashop2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //생성 메서드 사용 => setter, 생성자 막아두자 => protected 생성자,필요한 setter만 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        /*
        * CascadeType.ALL => delivery와 orderItem을 따로 persist해주지 않아도 자동으로 해준다.
        * => Cascade를 통해 persist되는 객체는 Cascade를 호출하는 객체로부터만 호출받아야한다.
        *
        * */
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
