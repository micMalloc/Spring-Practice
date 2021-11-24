package kr.heesu.practice.spring.core.advanced.config.v1_proxy.concrete_proxy;

import kr.heesu.practice.spring.core.advanced.proxy.application.v2.OrderServiceV2;
import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final LogTrace logTrace;
    private final OrderServiceV2 target;

    public OrderServiceConcreteProxy(LogTrace logTrace, OrderServiceV2 target) {
        super(null);
        this.logTrace = logTrace;
        this.target = target;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()"); //target 호출
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
