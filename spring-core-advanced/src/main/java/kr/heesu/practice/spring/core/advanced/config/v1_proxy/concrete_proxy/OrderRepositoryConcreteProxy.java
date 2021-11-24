package kr.heesu.practice.spring.core.advanced.config.v1_proxy.concrete_proxy;

import kr.heesu.practice.spring.core.advanced.proxy.application.v2.OrderRepositoryV2;
import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final LogTrace logTrace;
    private final OrderRepositoryV2 target;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            //target 호출
            target.save(itemId);

            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
