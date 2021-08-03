package kr.heesu.practice.webflux.clients.sms;

import kr.heesu.practice.webflux.clients.core.HostProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("hosts.notification")
public class NotificationHostProperties {

    private HostProperties sms;

}

