package kr.heesu.practice.webflux.clients.tistory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenResponse {

    @JsonProperty("access-token")
    private String accessToken;
}
