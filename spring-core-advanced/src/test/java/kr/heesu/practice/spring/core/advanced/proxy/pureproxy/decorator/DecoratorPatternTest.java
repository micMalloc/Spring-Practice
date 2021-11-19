package kr.heesu.practice.spring.core.advanced.proxy.pureproxy.decorator;

import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.decorator.code.Component;
import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.decorator.code.MessageDecorator;
import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.decorator.code.RealComponent;
import kr.heesu.practice.spring.core.advanced.proxy.pureproxy.decorator.code.TimeDecorator;
import org.junit.jupiter.api.Test;

class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);

        client.execute();
    }

    @Test
    void decorator1() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }

    @Test
    void decorator2() {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
