package brido.com.hashring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeTest {

  @Autowired
  EmployeeService employeeService;

  @Test
  void readOnly() {
    employeeService.getEmployBy(1L);
  }

  @Test
  void writeOnly() {
    employeeService.saveEmploy("hello");
  }
}
