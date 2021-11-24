package kr.heesu.practice.spring.core.advanced.proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteClient {

    private final ConcreteLogic concreteLogic;

    public void execute() {
        concreteLogic.operation();
    }
}
