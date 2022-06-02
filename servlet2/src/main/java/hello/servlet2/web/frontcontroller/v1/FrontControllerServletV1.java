package hello.servlet2.web.frontcontroller.v1;

import hello.servlet2.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet2.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet2.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    /*
    * 해당 서블릿이 생성될때, 아래의 URL을 가지는 ControllerV1을 생성해서 가지고 있음
    * */
    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//들어온 urlPattern을 그대로 String으로
        ControllerV1 findController = controllerV1Map.get(requestURI);
        if (findController == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        findController.process(request,response);
    }
}
