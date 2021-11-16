package kr.heesu.practice.spring.core.advanced.trace.application.v1;

import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final HelloTraceV1 trace;
    private final OrderRepositoryV1 orderRepository;

    public void orderItem(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
