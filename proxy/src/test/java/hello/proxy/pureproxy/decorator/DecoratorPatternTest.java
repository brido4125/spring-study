package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecoratorTest() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.excute();
    }

    @Test
    void decorator1(){
        DecoratorPatternClient client = new DecoratorPatternClient(new MessageDecorator(new RealComponent()));

        client.excute();
    }

    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        /*
        * 데코레이터 간의 순서 변경 가능
        * */
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.excute();
    }
}
