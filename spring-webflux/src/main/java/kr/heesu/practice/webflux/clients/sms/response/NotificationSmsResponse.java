package kr.heesu.practice.webflux.clients.sms.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.Timestamp;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationSmsResponse {

    private String requestId;
    private Timestamp requestTime;
    private String statusCode;
    private String statusName;

}
