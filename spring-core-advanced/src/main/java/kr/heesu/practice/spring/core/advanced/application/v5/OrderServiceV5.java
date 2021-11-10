package kr.heesu.practice.spring.core.advanced.application.v5;

import kr.heesu.practice.spring.core.advanced.trace.callback.TraceCallback;
import kr.heesu.practice.spring.core.advanced.trace.callback.TraceTemplate;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final TraceTemplate template;
    private final OrderRepositoryV5 orderRepository;

    public OrderServiceV5(LogTrace trace, OrderRepositoryV5 orderRepository) {
        this.template = new TraceTemplate(trace);
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", (TraceCallback<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
