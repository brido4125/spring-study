package jpabook.jpashop2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "deliver_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //연관관계 메서드
    //양방향 연관관계 => 두 쪽 모두 진행
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    //양방향 연관관계 => 두 쪽 모두 진행
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    protected void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    protected void setOrderDate(LocalDateTime localDateTime) {
        this.orderDate = localDateTime;
    }

    //생성 메소드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //주문 취소
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("배송 완료된 상품입니다.");
        }
        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int sum = 0;
        for (OrderItem orderItem : getOrderItems()) {
            sum += orderItem.getTotalPrice();
        }
        return sum;
    }


}

