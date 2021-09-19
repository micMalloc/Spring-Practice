package kr.heesu.practice.webflux.mono;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

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

    private Mono<Boolean> checkDuplicate() {
        return Mono.just(Boolean.TRUE);
    }
}
