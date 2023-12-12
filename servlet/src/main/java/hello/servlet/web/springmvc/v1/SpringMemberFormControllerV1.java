package hello.servlet.web.springmvc.v1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller// 스프링이 자동으로 스프링 빈으로 등록, RequestMappingHandlerMapping에서 인식하도록
//@RequestMapping + @Component 로 대체 가능
//RequestMappingHandlerMapping의 isHandelr() 메서드에서 판단
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
