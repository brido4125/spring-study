package brido.me.todo.service;

import brido.me.todo.model.TodoEntity;
import brido.me.todo.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("To do item").build();
        todoRepository.save(entity);
        TodoEntity todoEntity = todoRepository.findById(entity.getId()).get();
        return todoEntity.getId() +" " + todoEntity.getTitle();
    }

}
