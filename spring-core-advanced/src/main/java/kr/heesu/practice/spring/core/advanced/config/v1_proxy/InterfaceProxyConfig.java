package kr.heesu.practice.spring.core.advanced.config.v1_proxy;

import kr.heesu.practice.spring.core.advanced.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import kr.heesu.practice.spring.core.advanced.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import kr.heesu.practice.spring.core.advanced.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderControllerV1;
import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderControllerV1Impl;
import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderRepositoryV1;
import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderRepositoryV1Impl;
import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderServiceV1;
import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderServiceV1Impl;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1 controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(logTrace, controllerImpl);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1 serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(logTrace, serviceImpl);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1 repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(logTrace, repositoryImpl);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalTrace();
    }
}
