package hello.proxy.pureproxy.decorator.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private final Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator operation 실행");
        String operation = component.operation();
        String decoResult = "***" + operation + " + By MessageDecorator";
        log.info("decorator 적용 : {}", decoResult);
        return decoResult;
    }
}
