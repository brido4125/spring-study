package brido.me.todo.controller;

import brido.me.todo.dto.ResponseDTO;
import brido.me.todo.dto.TodoDTO;
import brido.me.todo.model.TodoEntity;
import brido.me.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String service = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(service);
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().error("400").data(list).build();
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String tempUser = "tmp-user";
            TodoEntity entity = TodoDTO.toEntity(dto);
            entity.setId(null);
            entity.setUserId(tempUser);
            List<TodoEntity> todoList = todoService.createTodo(entity);
            List<TodoDTO> todoDTOS = todoList.stream().map(TodoDTO::new).collect(Collectors.toList());
            ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(todoDTOS).build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            String message = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(message).build();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
