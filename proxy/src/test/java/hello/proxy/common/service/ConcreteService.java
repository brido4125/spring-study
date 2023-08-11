package hello.proxy.common.service;


import lombok.extern.slf4j.Slf4j;

/*
* 구체 클래스만 존재
* */
@Slf4j
public class ConcreteService {
    public void call() {
        log.info("Concrete Service call");
    }
}
