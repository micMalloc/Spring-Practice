package kr.heesu.practice.webflux.clients.tistory.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenRequest {

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String code;

    private String grantType = "authorization_code";

    public static AccessTokenRequest of(String clientId, String clientSecret, String redirectUri, String code) {
        return AccessTokenRequest.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectUri(redirectUri)
            .code(code)
            .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    private AccessTokenRequest(String clientId, String clientSecret, String redirectUri, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.code = code;
    }
}
