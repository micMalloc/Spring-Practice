package kr.heesu.practice.webflux.clients.config;

import kr.heesu.practice.webflux.clients.sms.NotificationApiClient;
import kr.heesu.practice.webflux.clients.sms.NotificationHostProperties;
import kr.heesu.practice.webflux.clients.sms.impl.NotificationApiClientImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({NotificationHostProperties.class})
public class ApiClientConfig {

    @Bean
    public NotificationApiClient notificationApiClient(WebClient.Builder builder, NotificationHostProperties notificationHostProperties) {
        return new NotificationApiClientImpl(builder, notificationHostProperties.getSms());
    }

}
