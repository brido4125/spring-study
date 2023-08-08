package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;


/*
* intent : 작업에서 부모클래스에 알고리즘의 골격을 정의하고 일부 단계를 하위 클래스로 미루는 패턴
* 상속 -> 강결합
* 자식 클래스 입장에서는 부모 클래스의 로직을 상속 받아야함
* 부모 클래스의 변경이 자식 클래스에 영향을 미칠 수 있음
* */
public abstract class AbstractTemplate<T> {
    private final LogTrace logTrace;

    public AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public T excute(String message) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            //로직 호출 -> 자식 클래스의 구현에 따라 달라짐
            T result = call();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
    protected abstract T call();
}
