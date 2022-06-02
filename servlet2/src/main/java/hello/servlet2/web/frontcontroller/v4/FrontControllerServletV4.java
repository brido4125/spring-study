package hello.servlet2.web.frontcontroller.v4;

import hello.servlet2.web.frontcontroller.ModelView;
import hello.servlet2.web.frontcontroller.MyView;
import hello.servlet2.web.frontcontroller.v3.ControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet2.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet2.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet2.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet2.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    /*
    * 해당 서블릿이 생성될때, 아래의 URL을 가지는 ControllerV1을 생성해서 가지고 있음
    * */
    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//들어온 urlPattern을 그대로 String으로
        ControllerV4 findController = controllerV4Map.get(requestURI);
        if (findController == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        HashMap<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();//modelView를 만들 필요 없이 model을 통해 바로 controller에 던지기 가능

        String viewName = findController.process(paramMap, model);//paramMap을 통해 modelview의 model 생성가능

        MyView view = viewResolver(viewName);//view-resolver 역할을 동시에 수행

        //기존에는 modelView에서 getModel을 통해 꺼내왔지만, 지금은 model 바로 사용가능
        view.render(model,request,response);

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
