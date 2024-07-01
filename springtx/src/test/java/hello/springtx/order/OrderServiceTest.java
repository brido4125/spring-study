package hello.springtx.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@SpringBootTest
class OrderServiceTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void order() throws NotEnoughMoneyException {
    Order order = new Order();
    order.setUsername("정상");

    orderService.order(order);

    Order find = orderRepository.findById(order.getId()).get();
    assertThat(find.getPayStatus()).isEqualTo("완료");
  }

  @Test
  void runtime() {
    Order order = new Order();
    order.setUsername("예외");

    assertThatThrownBy(() -> {
      orderService.order(order);
    }).isInstanceOf(RuntimeException.class);

    Optional<Order> byId = orderRepository.findById(order.getId());
    assertThat(byId.isEmpty()).isTrue();
  }

  @Test
  void bizException() {
    Order order = new Order();
    order.setUsername("잔고부족");

    try {
      orderService.order(order);
    } catch (NotEnoughMoneyException e) {
      log.info("고객에게 잔고 부족 노티");
    }

    Order find = orderRepository.findById(order.getId()).get();
    assertThat(find.getPayStatus()).isEqualTo("대기");
  }
}