package kr.heesu.practice.spring.core.advanced.trace.logtrace;

import kr.heesu.practice.spring.core.advanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
