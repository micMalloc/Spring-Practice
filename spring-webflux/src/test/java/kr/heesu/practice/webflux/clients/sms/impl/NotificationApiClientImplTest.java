package kr.heesu.practice.webflux.clients.sms.impl;

import kr.heesu.practice.webflux.clients.sms.NotificationApiClient;
import kr.heesu.practice.webflux.clients.sms.response.NotificationSmsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationApiClientImplTest {

    @Autowired NotificationApiClient notificationApiClient;

    @DisplayName("SMS 전송 테스트")
    @Test
    void sendSms() {
        // given
        String destination = "01030359199";
        String content = "SMS 테스트";

        // when
        NotificationSmsResponse response = notificationApiClient.sendSms(destination, content).block();

        // then
        System.out.println("response = " + response);
    }
}