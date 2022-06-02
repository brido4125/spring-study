package hello.servlet2.web.frontcontroller.v3;

import hello.servlet2.web.frontcontroller.ModelView;
import hello.servlet2.web.frontcontroller.MyView;
import hello.servlet2.web.frontcontroller.v2.ControllerV2;
import hello.servlet2.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet2.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet2.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet2.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    /*
    * 해당 서블릿이 생성될때, 아래의 URL을 가지는 ControllerV1을 생성해서 가지고 있음
    * */
    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//들어온 urlPattern을 그대로 String으로
        ControllerV3 findController = controllerV3Map.get(requestURI);
        if (findController == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        HashMap<String, String> paramMap = createParamMap(request);
        ModelView modelView = findController.process(paramMap);//paramMap을 통해 modelview의 model 생성가능

        String viewName = modelView.getViewName();

        MyView view = viewResolver(viewName);//view-resolver 역할을 동시에 수행
        //model을 request,response화 함께 render에 넘겨줌
        //render를 호출하면 넘어온 model을 통해 request에 setAttribute를 통해 심어줌
        view.render(modelView.getModel(),request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views" + viewName + ".jsp");
    }

    /*
    * httpSeverlet request에 있는 parameter를 전부 paramMap에 저장
    * */
    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
