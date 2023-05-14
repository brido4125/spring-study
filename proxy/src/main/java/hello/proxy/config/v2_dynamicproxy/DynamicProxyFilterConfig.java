package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFileterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) Proxy.newProxyInstance(
                orderController.getClass().getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFileterHandler(orderController, logTrace, PATTERNS));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1Impl orderService = new OrderServiceV1Impl(oderRepositoryV1(logTrace));
        return (OrderServiceV1) Proxy.newProxyInstance(
                orderService.getClass().getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFileterHandler(orderService, logTrace, PATTERNS));
    }

    @Bean
    public OrderRepositoryV1 oderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(
                orderRepository.getClass().getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFileterHandler(orderRepository, logTrace, PATTERNS));
    }
}
