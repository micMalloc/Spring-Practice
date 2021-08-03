package kr.heesu.practice.webflux.clients.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class HostProperties {

    private String protocol = "http";
    private String url;
    private String port;
    private Map<String, String> headers = new HashMap<>();

    private int socketTimeout = 3000;
    private int connectionTimeout = 3000;
    private int executionTimeout = 3000;

    public DefaultUriBuilderFactory toUriBuilderFactory() {
        return new DefaultUriBuilderFactory(
                UriComponentsBuilder
                        .newInstance()
                        .scheme(protocol)
                        .host(url)
                        .port(StringUtils.hasText(port) ? port : null)
        );
    }

    public Duration getExecutionTimeout() {
        return Duration.ofMillis(executionTimeout);
    }
}
