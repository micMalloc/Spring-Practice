package kr.heesu.practice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // Eureka 서버로서 등록
@SpringBootApplication
public class EurekaPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaPracticeApplication.class, args);
    }
}
