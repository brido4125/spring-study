package brido.me.todo.controller;

import brido.me.todo.dto.ResponseDTO;
import brido.me.todo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping
    public String testController() {
        return "OK";
    }

    @GetMapping("hello")
    public String testControllerWithPath() {
        return "Hello! OK";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "name's id : " + id;
    }

    @GetMapping("/requestParams")
    public String testControllerWithRequestParams(@RequestParam int age, @RequestParam String name) {
        return name + " " + age;
    }

    @GetMapping("/requestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO dto) {
        return dto.toString();
    }

    @GetMapping("/responseBody")
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("This is me!");
        return ResponseDTO.<String>builder().error("error message").data(list).build();
    }

    /**
     * ResponseEntity can control HTTP Header Status!
     * @return ResponseEntity with Status
     */
    @GetMapping("/responseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("response entity test");
        list.add("HttpResponse Status : 400");
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error("400").data(list).build();
        return ResponseEntity.status(400).body(responseDTO);
    }
}
