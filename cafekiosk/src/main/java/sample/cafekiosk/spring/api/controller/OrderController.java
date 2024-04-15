package sample.cafekiosk.spring.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.spring.api.controller.requset.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.OrderService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/api/v1/orders/new")
  public void createOrder(@RequestBody OrderCreateRequest request) {
    orderService.createOrder(request, LocalDateTime.now());
  }
}
