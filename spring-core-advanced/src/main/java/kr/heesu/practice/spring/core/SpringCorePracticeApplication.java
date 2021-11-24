package kr.heesu.practice.spring.core;

import kr.heesu.practice.spring.core.advanced.config.v1_proxy.ConcreteProxyConfig;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.LogTrace;
import kr.heesu.practice.spring.core.advanced.trace.logtrace.ThreadLocalTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = "kr.heesu.practice.spring.core.advanced.proxy")
public class SpringCorePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCorePracticeApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalTrace();
    }
}
