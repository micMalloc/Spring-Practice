package kr.heesu.practice.webflux.clients.tistory;

import kr.heesu.practice.webflux.clients.tistory.response.AccessTokenResponse;
import reactor.core.publisher.Mono;

public interface TistoryApiClient {

    Mono<AccessTokenResponse> getAccessToken(String clientId, String clientSecret, String redirectUri, String code);
}
