package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.*;

//Spring은 @Controller 또는 @RestController가 붙은 클래스를 찾아서 스프링 빈으로 등록한다.
@RequestMapping
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("v1/no-log")
    String noLog();
}
