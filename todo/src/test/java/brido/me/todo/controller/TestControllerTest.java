package brido.me.todo.controller;

import brido.me.todo.service.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestControllerTest {
    @Autowired private TestController testController;

    @Test
    void DITest() {
        Assertions.assertNotNull(testController);
        TestService testService = testController.getTestService();
        Assertions.assertNotNull(testService);
    }
}