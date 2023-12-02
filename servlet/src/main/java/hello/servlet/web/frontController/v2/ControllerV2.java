package hello.servlet.web.frontController.v2;

import hello.servlet.web.frontController.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    //V1과 다르게 rendering할 수 있는 MyView라는 객체를 리턴하도록 변경
    //즉, 기존에는 controller 구현체에서 view를 렌더링하도록 하였고
    //v2에서는 FrontController에서 view를 렌더링하도록 변경
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
