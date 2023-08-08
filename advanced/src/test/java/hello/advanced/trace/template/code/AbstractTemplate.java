package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        long startTime = System.currentTimeMillis();
        //biz logic
        call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    // 변하게 되는 부분 -> 자식이 만들면 됨
    // 추상 메서드로 자식 클래스마다 구현을 다르게 할 수 있음
    // 클래스를 생성해야하는 단점 -> 익명 클래스 or 람다로 해결
    protected abstract void call();
}
