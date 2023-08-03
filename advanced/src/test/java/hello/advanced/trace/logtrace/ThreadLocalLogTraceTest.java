package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {
    LogTrace trace = new ThreadLocalLogTrace();

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
    @DisplayName("Thread Local을 사용하면 Trace Id가 명확하게 구분된다.")
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