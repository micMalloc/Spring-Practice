package kr.heesu.practice.spring.core.advanced.config.v1_proxy.concrete_proxy;

import kr.heesu.practice.spring.core.advanced.proxy.application.v2.OrderControllerV2;
import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final LogTrace logTrace;
    private final OrderControllerV2 target;

    public OrderControllerConcreteProxy(LogTrace logTrace, OrderControllerV2 target) {
        super(null);
        this.logTrace = logTrace;
        this.target = target;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.request()");

            //target 호출
            String result = target.request(itemId);
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
