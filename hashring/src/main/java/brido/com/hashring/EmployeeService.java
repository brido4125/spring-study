package brido.com.hashring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Transactional(readOnly = true)
  public Employee getEmployBy(Long id) {
    return employeeRepository.findById(id).orElseThrow();
  }

  @Transactional
  public void saveEmploy(String name) {
    employeeRepository.save(new Employee(name));
  }


}
