package kr.heesu.practice.spring.core.advanced.config;

import kr.heesu.practice.spring.core.advanced.trace.logtrace.FieldLogTrace;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
