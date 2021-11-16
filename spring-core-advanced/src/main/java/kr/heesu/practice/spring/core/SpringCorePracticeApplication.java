package kr.heesu.practice.spring.core;

import kr.heesu.practice.spring.core.advanced.config.AppV1Config;
import kr.heesu.practice.spring.core.advanced.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppV1Config.class, AppV2Config.class})
@SpringBootApplication(scanBasePackages = "kr.heesu.practice.spring.core.advanced.proxy")
public class SpringCorePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCorePracticeApplication.class, args);
    }
}
