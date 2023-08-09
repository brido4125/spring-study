package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
* proxy 객체는 스프링 컨테이너가 관리하고 Heap 영역에 올라감,
* target 객체는 proxy 객체가 생성될 때 생성자를 통해 주입받음, 스프링 컨테이너가 관리하진 않고 Heap 영역에 올라감
* */
@Configuration
public class InterfaceProxyConfig {

    /*
    * BeanName : orderController
    * BeanObject : OrderControllerInterfaceProxy
    * */
    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }


    /*
    * BeanName : orderService
    * BeanObject : OrderServiceInterfaceProxy
    * */
    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }



    /*
    * BeanName : orderRepository
    * BeanObject : OrderRepositoryInterfaceProxy
    * */
    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }
}
