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
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

  private final ProductRepository productRepository;

  private final OrderRepository orderRepository;

  @Transactional
  public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime localDateTime) {
    List<String> productNumbers = request.getProductNumbers();

    Order order = Order.create(getProducts(productNumbers), localDateTime);
    Order save = orderRepository.save(order);

    return OrderResponse.of(save);
  }

  private List<Product> getProducts(List<String> productNumbers) {
    List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
    Map<String, Product> productMap = products.stream()
            .collect(Collectors.toMap(Product::getProductNumber, p -> p));
    List<Product> duplicatedProduct = productNumbers.stream()
            .map(pn -> productMap.get(pn))
            .collect(Collectors.toList());
    return duplicatedProduct;
  }
}
