package kr.heesu.practice.webflux.clients.sms;

import kr.heesu.practice.webflux.clients.sms.response.NotificationSmsResponse;
import reactor.core.publisher.Mono;

public interface NotificationApiClient {

    Mono<NotificationSmsResponse> sendSms(String destination, String message);

}
