package sample.cafekiosk.spring.api.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import sample.cafekiosk.spring.api.controller.requset.OrderCreateRequest;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

@DataJpaTest
class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductRepository productRepository;

  @DisplayName("주문 번호 리스트를 받아 주문을 생성한다.")
  @Test
  void createOrder() {
    //given
    Product product1 = createProduct(HANDMADE, "001", 1000);
    Product product2 = createProduct(HANDMADE, "002", 3000);
    Product product3 = createProduct(HANDMADE, "003", 5000);

    productRepository.saveAll(List.of(product1, product2, product3));
    OrderCreateRequest request
            = OrderCreateRequest
            .builder()
            .productNumbers(List.of("001", "002"))
            .build();
    //when
    OrderResponse orderResponse = orderService.createOrder(request);
    //then
    assertThat(orderResponse.getId()).isNotNull();
    assertThat(orderResponse.getId())
            .extracting("registeredDateTime", "totalPrice")
            .contains(LocalDateTime.now(), 4000);
    assertThat(orderResponse.getProducts()).hasSize(2)
            .extracting("productName", "price")
            .containsExactly(
                    tuple("001", 1000),
                    tuple("002", 3000)
            );
  }

  private Product createProduct(ProductType type, String productNumber, int price) {
    return Product.builder()
            .type(type)
            .productNumber(productNumber)
            .price(price)
            .sellingStatus(SELLING)
            .name("메뉴이름")
            .build();
  }
}