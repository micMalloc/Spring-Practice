package kr.heesu.practice.spring.core.advanced.config;

import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalTrace();
    }
}
