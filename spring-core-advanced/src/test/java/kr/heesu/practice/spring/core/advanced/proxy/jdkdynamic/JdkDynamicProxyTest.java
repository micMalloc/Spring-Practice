package kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic;

import kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic.code.AInterface;
import kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic.code.Aimpl;
import kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic.code.BImpl;
import kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic.code.BInterface;
import kr.heesu.practice.spring.core.advanced.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        // given
        AInterface target = new Aimpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        // when
        proxy.call();

        // then
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        // given
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        // when
        proxy.call();

        // then
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
