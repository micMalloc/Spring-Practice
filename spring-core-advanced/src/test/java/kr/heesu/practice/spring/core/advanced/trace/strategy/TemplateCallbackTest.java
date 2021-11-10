package kr.heesu.practice.spring.core.advanced.trace.strategy;

import kr.heesu.practice.spring.core.advanced.trace.strategy.code.template.Callback;
import kr.heesu.practice.spring.core.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateCallbackTest {

    @DisplayName("템플릿 콜백 패턴 - 익명 클래스")
    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니즈1 호출");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니즈2 호출");
            }
        });
    }

    @DisplayName("템플릿 콜백 패턴 - 람다")
    @Test
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(() -> log.info("비즈니즈1 호출"));

        template.execute(() -> log.info("비즈니즈2 호출"));
    }
}
