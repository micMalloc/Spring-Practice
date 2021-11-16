package kr.heesu.practice.spring.core.advanced.trace.application.v4;

import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import kr.heesu.practice.spring.core.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final LogTrace trace;
    private final OrderServiceV4 orderService;

    @GetMapping("/v4/request")
    public String request(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
