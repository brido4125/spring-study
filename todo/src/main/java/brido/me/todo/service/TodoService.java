package brido.me.todo.service;

import brido.me.todo.dto.TodoDTO;
import brido.me.todo.model.TodoEntity;
import brido.me.todo.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public List<TodoEntity> createTodo(final TodoEntity entity) {
        validation(entity);
        todoRepository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());
        return todoRepository.findByUserId(entity.getUserId());
    }

    private void validation(TodoEntity entity) {
        //Validation
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
