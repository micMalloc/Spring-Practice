package kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Aimpl implements AInterface {

    @Override
    public String call() {
        log.info("A 호출");
        return "a";
    }
}
