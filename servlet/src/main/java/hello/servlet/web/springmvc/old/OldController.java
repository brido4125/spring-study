package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller 방식으로 사용하는 것이 아닌 Controller라는 Interface의 구현체를 Bean으로 등록하여 사용하는 방식
@Component("/springmvc/old-controller")//spring bean 이름
public class OldController implements Controller {
    //DispatcherServlet에서 찾아온 HandlerAdaptor의 실제 handle() 메서드를 호출 Line 1067
    //SimpleControllerHandlerAdapter의 handle이 호출하는 메서드가 handleRequest
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
