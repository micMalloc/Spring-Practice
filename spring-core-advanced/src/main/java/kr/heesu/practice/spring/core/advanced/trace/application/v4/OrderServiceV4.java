package kr.heesu.practice.spring.core.advanced.trace.application.v4;

import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import kr.heesu.practice.spring.core.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final LogTrace trace;
    private final OrderRepositoryV4 orderRepository;

    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    }
}
