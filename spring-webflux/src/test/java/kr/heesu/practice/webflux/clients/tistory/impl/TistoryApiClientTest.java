package kr.heesu.practice.webflux.clients.tistory.impl;

import kr.heesu.practice.webflux.clients.tistory.TistoryApiClient;
import kr.heesu.practice.webflux.clients.tistory.response.AccessTokenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TistoryApiClientTest {

    @Autowired TistoryApiClient tistoryApiClient;

    @DisplayName("어세스 토큰 획득")
    @Test
    void getAccessToken() {
        // given
        String id = "638935f9c46bf328b4e35c91306f49f4";
        String secret = "638935f9c46bf328b4e35c91306f49f43efcba81ca380abe45dda8bb9f8e5408cf7d4f42\n";
        String redirect = "http://localhost:8080";
        String code = "code";

        // when
        AccessTokenResponse response = tistoryApiClient.getAccessToken(id, secret, redirect, code).block();

        // then
        assertNotNull(response);
        System.out.println("response = " + response.getAccessToken());
    }
}