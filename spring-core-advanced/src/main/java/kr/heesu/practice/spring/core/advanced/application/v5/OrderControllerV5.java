package kr.heesu.practice.spring.core.advanced.application.v5;

import kr.heesu.practice.spring.core.advanced.trace.callback.TraceTemplate;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final TraceTemplate template;
    private final OrderServiceV5 orderService;

    public OrderControllerV5(LogTrace trace, OrderServiceV5 orderService) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
