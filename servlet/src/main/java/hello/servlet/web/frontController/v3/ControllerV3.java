package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;

import java.util.Map;

public interface ControllerV3 {
    //서블릿에 대한 의존성 제거
    ModelView process(Map<String, String> paramMap);
}
