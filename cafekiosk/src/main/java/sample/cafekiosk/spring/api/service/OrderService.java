package sample.cafekiosk.spring.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.requset.OrderCreateRequest;

@Service
public class OrderService {

  @Transactional
  public OrderResponse createOrder(OrderCreateRequest request) {
    return null;
  }
}
