package kr.heesu.practice.spring.core.advanced.application.v3;

import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final LogTrace trace;
    private final OrderServiceV3 orderService;

    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
