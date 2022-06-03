package hello.servlet2.web.frontcontroller.v5.adaptor;

import hello.servlet2.web.frontcontroller.ModelView;
import hello.servlet2.web.frontcontroller.v3.ControllerV3;
import hello.servlet2.web.frontcontroller.v4.ControllerV4;
import hello.servlet2.web.frontcontroller.v5.MyHandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class ControllerV4HandlerAdaptor implements MyHandlerAdaptor {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        HashMap<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();//modelView를 만들 필요 없이 model을 통해 바로 controller에 던지기 가능

        String viewName = controller.process(paramMap, model);

        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

        return modelView;
    }

    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
