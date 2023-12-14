package brido.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RestController => String return
 * Controller => view name return
 */
@Slf4j
@RestController
public class LogTestController {
    //private final Logger logger = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "brido";

        //log.trace("trace log = " + name); => 불필요한 String 더하기 연산 발생해서 메모리 낭비 발생
        log.trace("trace log = {}",name);
        log.debug("debug log = {}",name);
        log.info("info log is={}", name);
        log.warn("warn log = {}",name);
        log.error("error log = {}",name);
        return "test";
    }
}
