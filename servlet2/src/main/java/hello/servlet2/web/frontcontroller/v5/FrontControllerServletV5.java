package hello.servlet2.web.frontcontroller.v5;

import hello.servlet2.web.frontcontroller.ModelView;
import hello.servlet2.web.frontcontroller.MyView;
import hello.servlet2.web.frontcontroller.v3.ControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet2.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet2.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet2.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet2.web.frontcontroller.v5.adaptor.ControllerV3HandlerAdaptor;
import hello.servlet2.web.frontcontroller.v5.adaptor.ControllerV4HandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    private final List<MyHandlerAdaptor> handlerAdaptors = new LinkedList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdaptors();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdaptors() {
        handlerAdaptors.add(new ControllerV3HandlerAdaptor());
        handlerAdaptors.add(new ControllerV4HandlerAdaptor());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdaptor adaptor = getHandlerAdaptor(handler);

        ModelView mv = adaptor.handle(request, response, handler);

        String viewName = mv.getViewName();

        MyView view = viewResolver(viewName);//view-resolver 역할을 동시에 수행
        //model을 request,response화 함께 render에 넘겨줌
        //render를 호출하면 넘어온 model을 통해 request에 setAttribute를 통해 심어줌
        view.render(mv.getModel(),request, response);
    }

    private MyHandlerAdaptor getHandlerAdaptor(Object handler) {
        MyHandlerAdaptor a;
        for (MyHandlerAdaptor adaptor : handlerAdaptors) {
            if (adaptor.supports(handler)) {
               return adaptor;
            }
        }
        throw new IllegalArgumentException("handler adaptor를 찾을 수 없습니다." + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();//들어온 urlPattern을 그대로 String으로
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views" + viewName + ".jsp");
    }

}
