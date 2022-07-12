package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyCheckedException.class);
    }

    /*
     * Exception을 상속 받은 예외는 Check 예외 (컴파일러가 확인)
     * */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /*
    * Check 예외는 잡거나 던지거나 둘 중 하나가 필수
    * */
    static class Service {
        Repository repository = new Repository();

        /*
         * check 예외를 잡아서 처리함
         * */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외처리로직
                log.info("Exception message = {}",e.getMessage(),e);
            }
        }

        /*
        * check 예외를 잡아서 던짐
        * */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }

    }

    static class Repository {
        //check 예외는 던질 때, 던진다고 선언해줘야함 => 선언안되있을 경우 체크해줘서 체크 예외
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

}
