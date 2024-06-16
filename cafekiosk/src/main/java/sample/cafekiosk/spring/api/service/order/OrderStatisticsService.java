package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.mail.MailService;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
//Transactional? -  트랜잭션을 걸면 service의 비지니스 로직 내에서 IO에 의한 블로킹 시, DB 커넥션을 길게 잡고 있어서 굳이 걸 필요 X
public class OrderStatisticsService {

    private final OrderRepository orderRepository;

    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {
        // 해당 일자에 결제 완료된 주문들을 가져와서
        List<Order> orders = orderRepository.findOrdersBy(orderDate.atStartOfDay(),
            orderDate.plusDays(1).atStartOfDay(),
            OrderStatus.PAYMENT_COMPLETED);
        // 총 매출 합계를 계산하고
        int sum = orders.stream()
            .mapToInt(Order::getTotalPrice)
            .sum();
        // 메일을 전송한다.
        boolean result = mailService.sendMail("no-reply@test.com", email,
            String.format(" [매출통계]%s", orderDate),
            String.format("총 매출합게는 %s입니다.", sum));

        if (!result) {
            throw new IllegalArgumentException("메일 전송 실패");
        }
        return true;
    }
}
