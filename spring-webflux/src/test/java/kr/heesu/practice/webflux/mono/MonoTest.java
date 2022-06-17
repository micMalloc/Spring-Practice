package kr.heesu.practice.webflux.mono;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MonoTest {

    @DisplayName("")
    @Test
    void repeatWhenIfEmpty() {
        // given

        // when
        String filename = "filename";

        Mono.just(filename)
            .flatMap(name -> checkDuplicate());
        // then
    }

    @DisplayName("")
    @Test
    void delayElementTest() {
        // given
        Mono<String> data = Mono.just("DATA");

        // when
        data.delaySubscription(Duration.ofSeconds(3)).log();

        // then
        StepVerifier.create(data)
                .assertNext(msg -> {
                    log.info("MSG: {}", msg);
                    assertEquals(msg, "DATA");
                })
                .verifyComplete();
    }

    private Mono<Boolean> checkDuplicate() {
        return Mono.just(Boolean.TRUE);
    }
}
