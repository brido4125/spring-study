package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        //Lamda로 Override 대신 사용
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };
        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //FieldService.sleep(2000); //동기적으로 코드가 실행됨
        FieldService.sleep(100); // 동시성 문제 발생
        threadB.start();
        FieldService.sleep(3000);//메인 쓰레드가 종료되어 threadB가 종료되는것을 방지
        log.info("main exit");
    }
}
