package sample.cafekiosk.spring.api.service.reponse;

import lombok.Getter;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.product.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {
  private Long id;
  private int totalPrice;
  private LocalDateTime registeredDateTime;
  private List<ProductResponse> products;

  public OrderResponse(Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products) {
    this.id = id;
    this.totalPrice = totalPrice;
    this.registeredDateTime = registeredDateTime;
    this.products = products;
  }

  public static OrderResponse of(Order order) {
    return new OrderResponse(order.getId(),
        order.getTotalPrice(),
        order.getRegisteredDateTime(),
        order.getOrderProducts().stream().map(op -> ProductResponse.of(op.getProduct())).collect(Collectors.toList()));
  }
}
