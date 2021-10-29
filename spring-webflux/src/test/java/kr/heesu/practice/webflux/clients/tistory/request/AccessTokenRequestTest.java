package kr.heesu.practice.webflux.clients.tistory.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccessTokenRequestTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        // given
        String id = "client-id";
        String secret = "client-secret";
        String redirect = "redirect-uri";
        String code = "code";

        // when
        AccessTokenRequest request = AccessTokenRequest.of(id, secret, redirect, code);

        // then
        System.out.println("grant_type = " + request.getGrantType());
    }
}