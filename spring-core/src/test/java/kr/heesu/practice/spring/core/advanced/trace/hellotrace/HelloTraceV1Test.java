package kr.heesu.practice.spring.core.advanced.trace.hellotrace;

import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    void beginEnd() {
        // given
        HelloTraceV1 trace = new HelloTraceV1();

        // when
        TraceStatus status = trace.begin("hello");

        // then
        trace.end(status);
    }

    @Test
    void beginException() {
        // given
        HelloTraceV1 trace = new HelloTraceV1();

        // when
        TraceStatus status = trace.begin("hello");

        // then
        trace.exception(status, new IllegalStateException());
    }
}