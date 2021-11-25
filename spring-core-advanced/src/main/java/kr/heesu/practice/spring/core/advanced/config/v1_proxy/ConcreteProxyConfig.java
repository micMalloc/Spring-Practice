package kr.heesu.practice.spring.core.advanced.config.v1_proxy;

import kr.heesu.practice.spring.core.advanced.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import kr.heesu.practice.spring.core.advanced.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import kr.heesu.practice.spring.core.advanced.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import kr.heesu.practice.spring.core.advanced.proxy.application.v2.OrderControllerV2;
import kr.heesu.practice.spring.core.advanced.proxy.application.v2.OrderRepositoryV2;
import kr.heesu.practice.spring.core.advanced.proxy.application.v2.OrderServiceV2;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(logTrace, controllerImpl);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(logTrace, serviceImpl);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(logTrace, repositoryImpl);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalTrace();
    }
}
