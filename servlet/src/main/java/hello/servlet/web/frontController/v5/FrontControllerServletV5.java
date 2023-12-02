package hello.servlet.web.frontController.v5;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontController.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontController.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //기존에는 특정 구현 타입이 value의 타입으로 설정 되었지만,
    //V5에서는 여러 타입의 value 타입을 받을 수 있어야 하기에 Object로 설정
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //adaptor 보관용 List
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    //직접 초기화 HandlerAdapter 인스턴스를 주입시켜주면서 초기화
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adaptor = getHandlerAdaptor(handler);
        ModelView modelView = adaptor.handle(request, response, handler);

        String viewPath = modelView.getViewName();
        MyView myView = viewResolver(viewPath);

        myView.render(modelView.getModel(),request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        return handlerMappingMap.get(request.getRequestURI());
    }

    private MyHandlerAdapter getHandlerAdaptor(Object handler) {
        return handlerAdapters.stream()
            .filter(myHandlerAdapter -> myHandlerAdapter.supports(handler))
            .findAny().orElseThrow(() -> new IllegalArgumentException("handler adaptor 찾을 수 없음" + handler));
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}


