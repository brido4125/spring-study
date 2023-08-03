package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;//의존관계 주입

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
