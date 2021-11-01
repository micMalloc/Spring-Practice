package kr.heesu.practice.spring.core.advanced.trace.hellotrace;

import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void beginEnd() {
        // given
        HelloTraceV2 trace = new HelloTraceV2();

        // when
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        // then
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void beginException() {
        // given
        HelloTraceV2 trace = new HelloTraceV2();

        // when
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        // then
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}