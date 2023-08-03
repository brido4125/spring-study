package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {
    final FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

    @Test
    @DisplayName("동일한 FieldLogTrace 인스턴스를 사용하면 trace Id 동시성 문제가 발생한다.")
    void concurrentException() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            TraceStatus status1 = trace.begin("hello1 " + Thread.currentThread().getName());
            TraceStatus status2 = trace.begin("hello2 " + Thread.currentThread().getName());
            trace.exception(status2, new IllegalStateException());
            trace.exception(status1, new IllegalStateException());
        });

        Thread threadB = new Thread(() -> {
            TraceStatus status1 = trace.begin("hello1" + Thread.currentThread().getName());
            TraceStatus status2 = trace.begin("hello2" + Thread.currentThread().getName());
            trace.exception(status2, new IllegalStateException());
            trace.exception(status1, new IllegalStateException());
        });
        threadA.start();
        threadB.start();

        Thread.sleep(1000);
    }


}