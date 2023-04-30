package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        //Lamda로 Override 대신 사용
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //FieldService.sleep(2000); //동기적으로 코드가 실행됨
        FieldService.sleep(100); //동기적으로 코드가 실행됨
        threadB.start();
        FieldService.sleep(3000);//메인 쓰레드가 종료되어 threadB가 종료되는것을 방지
        log.info("main exit");
    }
}
