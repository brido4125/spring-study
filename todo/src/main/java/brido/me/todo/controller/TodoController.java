package brido.me.todo.controller;

import brido.me.todo.dto.ResponseDTO;
import brido.me.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String service = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(service);
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error("400").data(list).build();
        return ResponseEntity.ok().body(responseDTO);
    }
}
