package sample.cafekiosk.spring.api.controller.requset;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {
  private List<String> productNumbers;

  @Builder
  private OrderCreateRequest(List<String> productNumbers) {
    this.productNumbers = productNumbers;
  }

}
