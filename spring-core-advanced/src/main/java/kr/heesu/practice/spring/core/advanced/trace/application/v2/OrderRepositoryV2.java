package kr.heesu.practice.spring.core.advanced.trace.application.v2;

import kr.heesu.practice.spring.core.advanced.trace.TraceId;
import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import kr.heesu.practice.spring.core.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId.createNextId(), "OrderRepository.save()");

            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int interval) {
        try {
            TimeUnit.MILLISECONDS.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
