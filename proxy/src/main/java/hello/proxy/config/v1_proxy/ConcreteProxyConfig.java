package hello.proxy.config.v1_proxy;


import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    /*
     * BeanName : orderController
     * BeanObject : OrderControllerConcreteProxy
     * */
    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderServiceV2(logTrace)); //proxy가 호출할 target 생성
        return new OrderControllerConcreteProxy(controllerImpl, logTrace);
    }


    /*
     * BeanName : orderService
     * BeanObject : OrderServiceConcreteProxy
     * */
    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepositoryV2(logTrace)); //proxy가 호출할 target 생성
        return new OrderServiceConcreteProxy(serviceImpl, logTrace);
    }


    /*
     * BeanName : orderRepository
     * BeanObject : OrderRepositoryConcreteProxy
     * */
    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2(); //proxy가 호출할 target 생성
        return new OrderRepositoryConcreteProxy(repositoryImpl, logTrace);
    }
}
