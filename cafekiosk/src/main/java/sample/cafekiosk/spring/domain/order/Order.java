package sample.cafekiosk.spring.domain.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk.spring.domain.product.Product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  private int totalPrice;

  private LocalDateTime registeredDateTime;

  //order 객체와 생명주기 동일하게, order는 OrderProduct 내의 field 명
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProducts = new ArrayList<>();

  public Order(List<Product> products, LocalDateTime registeredDateTime) {
    this.orderStatus = OrderStatus.INIT;
    this.totalPrice = calculateTotalPrice(products);
    this.registeredDateTime = registeredDateTime;
    this.orderProducts = products.stream()
        .map(product -> new OrderProduct(this, product))
        .collect(Collectors.toList());
  }

  public static Order create(List<Product> products, LocalDateTime localDateTime) {
    return new Order(products, localDateTime);
  }

  private int calculateTotalPrice(List<Product> products) {
    return products.stream()
        .mapToInt(Product::getPrice)
        .sum();
  }
}
