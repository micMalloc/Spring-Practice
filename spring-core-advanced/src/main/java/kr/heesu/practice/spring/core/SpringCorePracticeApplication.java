package kr.heesu.practice.spring.core;

import kr.heesu.practice.spring.core.advanced.config.v2_dynmaicproxy.DynamicProxyFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(DynamicProxyFilterConfig.class)
@SpringBootApplication(scanBasePackages = "kr.heesu.practice.spring.core.advanced.proxy")
public class SpringCorePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCorePracticeApplication.class, args);
    }
}
