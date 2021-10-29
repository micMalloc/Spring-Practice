package kr.heesu.practice.webflux.clients.tistory;

import kr.heesu.practice.webflux.clients.core.HostProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("hosts.blog")
public class TistoryHostProperties {

    private HostProperties tistory;
}
