package hello.servlet2.web.frontcontroller.v3;

import hello.servlet2.web.frontcontroller.ModelView;

import java.util.Map;

/*
* 기존의 HttpServletReq,res가 들어가지 않음
* 즉, Controller의 servlet 종속성을 없애주도록 리팩토링
* */
public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
