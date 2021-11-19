package kr.heesu.practice.spring.core.advanced.config.v1_proxy.interface_proxy;

import kr.heesu.practice.spring.core.advanced.proxy.application.v1.OrderRepositoryV1;
import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final LogTrace logTrace;
    private final OrderRepositoryV1 target;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.request()");
            //target 호출
            target.save(itemId);

            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
        }
    }
}
