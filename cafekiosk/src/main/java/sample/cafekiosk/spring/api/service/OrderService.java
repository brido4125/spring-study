package sample.cafekiosk.spring.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.requset.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.reponse.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

  private final ProductRepository productRepository;

  private final OrderRepository orderRepository;

  @Transactional
  public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime localDateTime) {
    List<String> productNumbers = request.getProductNumbers();
    //Product
    List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
    Order order = Order.create(products, localDateTime);
    Order save = orderRepository.save(order);
    //Order
    return OrderResponse.of(save);
  }
}
