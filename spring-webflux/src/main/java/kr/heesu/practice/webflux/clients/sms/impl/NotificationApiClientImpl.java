package kr.heesu.practice.webflux.clients.sms.impl;

import kr.heesu.practice.webflux.clients.core.ApiClient;
import kr.heesu.practice.webflux.clients.core.HostProperties;
import kr.heesu.practice.webflux.clients.sms.NotificationApiClient;
import kr.heesu.practice.webflux.clients.sms.request.NotificationSmsRequest;
import kr.heesu.practice.webflux.clients.sms.response.NotificationSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class NotificationApiClientImpl extends ApiClient implements NotificationApiClient {

    private static final String SERVICE_ID = "ncp:sms:kr:270422560992:heesu";
    private static final String ACCESS_KEY = "WDiEMQzahhkzSXn5YBv1";
    private static final String SECRET_KEY = "iV3GypegeSNKPVUogoF7vF4OKb3W0GZXKDDgbjmY";

    private static final String SENDER = "01055509031";

    public NotificationApiClientImpl(WebClient.Builder builder, HostProperties hostProperties) {
        super(builder, hostProperties);
    }

    @Override
    public Mono<NotificationSmsResponse> sendSms(String destination, String content) {
        long time = System.currentTimeMillis();
        List<NotificationSmsRequest.Message> messages =  new ArrayList<>();

        messages.add(NotificationSmsRequest.Message.of(destination, content));
        NotificationSmsRequest request = NotificationSmsRequest.of(SENDER, messages);

        try {
            String sig = makeSignature(time);
            log.info("sig -> " + sig);
            log.info("timestamp:{} \n signature:{}", time, sig);

            return webClient.post()
                    .uri("/sms/v2/services/{serviceId}/messages", SERVICE_ID)
                    .headers(headers -> {
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.set("x-ncp-apigw-timestamp", Long.toString(time));
                        headers.set("x-ncp-iam-access-key", ACCESS_KEY);
                        headers.set("x-ncp-apigw-signature-v2", sig);
                    })
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(NotificationSmsResponse.class).log()
                    .doOnError(WebClientResponseException.class, throwable -> log.error("[SMS] message = {}", throwable.getResponseBodyAsString(), throwable))
                    .timeout(timeout);

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            return Mono.error(new RuntimeException("SMS 전송 실패 {}", e.getCause()));
        }
    }

    public String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String space = " "; // one space
        String newLine = "\n"; // new line
        String method = "POST"; // method
        String url = "/sms/v2/services/" + SERVICE_ID +"/messages"; // url (include query string)
        String timestamp = time.toString(); // current timestamp (epoch)
        String accessKey = ACCESS_KEY; // access key id (from portal or Sub Account)
        String secretKey = SECRET_KEY;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

        return encodeBase64String;
    }
}
