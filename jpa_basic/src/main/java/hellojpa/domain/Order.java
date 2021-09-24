package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
//@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
