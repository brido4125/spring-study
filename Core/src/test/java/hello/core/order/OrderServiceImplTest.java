package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

//setter를 이용한 DI를 할때의 테스트 코드는 final 키워드를 제거해야 사용가능
public class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L,"daebak", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository,new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 1000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
