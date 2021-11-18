package kr.heesu.practice.spring.core.advanced.proxy.pureproxy.proxy;

import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.proxy.code.CacheProxy;
import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.proxy.code.ProxyPatternClient;
import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
