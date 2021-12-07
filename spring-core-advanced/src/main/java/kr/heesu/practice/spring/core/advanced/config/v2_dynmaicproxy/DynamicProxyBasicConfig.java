package kr.heesu.practice.spring.core.advanced.config.v2_dynmaicproxy;

import kr.heesu.practice.spring.core.advanced.config.v2_dynmaicproxy.handler.LogTraceBasicHandler;
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

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));

        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderController, logTrace));
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderService, logTrace));
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                        new Class[]{OrderRepositoryV1.class},
                        new LogTraceBasicHandler(orderRepository, logTrace));
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalTrace();
    }
}
