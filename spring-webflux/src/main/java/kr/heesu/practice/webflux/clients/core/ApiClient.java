package kr.heesu.practice.webflux.clients.core;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApiClient {

    protected final WebClient webClient;
    protected final Duration timeout;

    public ApiClient(WebClient.Builder builder, HostProperties hostProperties) {
        this.webClient = create(builder, hostProperties);
        this.timeout = hostProperties.getExecutionTimeout();
    }

    private WebClient create(WebClient.Builder webClientBuilder, HostProperties hostProperties) {

        WebClient.Builder builder = webClientBuilder
                .uriBuilderFactory(hostProperties.toUriBuilderFactory());
        hostProperties.getHeaders().forEach(builder::defaultHeader);

        return builder
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, hostProperties.getConnectionTimeout())
                                .doOnConnected(connection -> connection
                                        .addHandlerLast(new ReadTimeoutHandler(hostProperties.getSocketTimeout(), TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(hostProperties.getSocketTimeout(), TimeUnit.MILLISECONDS))
                                )
                )).build();
    }
}
