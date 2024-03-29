package sample.cafekiosk.spring.api.service;

import lombok.Getter;
import sample.cafekiosk.spring.domain.product.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {
  private Long id;
  private int totalPrice;
  private LocalDateTime registeredDateTime;
  private List<ProductResponse> products;
}
