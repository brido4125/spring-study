package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    //인자로 넘어오는 handler의 controller 구현 타입을 보고 자기 자신이 지원할 수 있는지 판별
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //handler가 supports 메서드를 통해 형변환 가능한 것을 보장받음
        ControllerV3 controllerV3 = (ControllerV3) handler;
        //request에 있는 param들을 뽑아내서 Map 형태로 반환하는 메서드 필요
        Map<String, String> paramMap = createParamMap(request);
        return controllerV3.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(
                paramName -> paramMap.put(paramName, request.getParameter(paramName))
        );
        return paramMap;
    }
}
