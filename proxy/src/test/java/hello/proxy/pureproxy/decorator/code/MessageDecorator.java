package hello.proxy.pureproxy.decorator.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator implements Component {

    public MessageDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("MessageDecorator operation 실행");
        String operation = component.operation();
        log.info("decorator 적용 전: {}", operation);
        String decoResult = "***" + operation + " + By MessageDecorator";
        log.info("decorator 적용 후: {}", decoResult);
        return decoResult;
    }
}
