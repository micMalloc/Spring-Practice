package kr.heesu.practice.webflux.clients.tistory.impl;

import kr.heesu.practice.webflux.clients.core.ApiClient;
import kr.heesu.practice.webflux.clients.core.HostProperties;
import kr.heesu.practice.webflux.clients.tistory.TistoryApiClient;
import kr.heesu.practice.webflux.clients.tistory.response.AccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
public class TistoryApiClientImpl extends ApiClient implements TistoryApiClient {

    public TistoryApiClientImpl(WebClient.Builder builder, HostProperties hostProperties) {
        super(builder, hostProperties);
    }

    @Override
    public Mono<AccessTokenResponse> getAccessToken(String clientId, String clientSecret, String redirectUri, String code) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/oauth/access_token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("code", code)
                .queryParam("grant_type", "authorize_code")
                .build())
            .retrieve()
            .bodyToMono(AccessTokenResponse.class)
            .doOnError(WebClientResponseException.class, throwable -> log.error("[TISTORY] message = {}", throwable.getResponseBodyAsString(), throwable))
            .timeout(timeout);
    }
}
