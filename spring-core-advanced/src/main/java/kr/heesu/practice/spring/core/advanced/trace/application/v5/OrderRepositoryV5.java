package kr.heesu.practice.spring.core.advanced.trace.application.v5;

import kr.heesu.practice.spring.core.advanced.trace.callback.TraceTemplate;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int interval) {
        try {
            TimeUnit.MILLISECONDS.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
