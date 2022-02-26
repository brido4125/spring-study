package brido.me.todo.service;

import brido.me.todo.model.TodoEntity;
import brido.me.todo.persistence.TodoRepository;
import com.google.errorprone.annotations.RequiredModifiers;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TodoServiceTest")
class TodoServiceTest {
    private final TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        todoService = new TodoService(todoRepository);
    }

    @Test
    void createTodo() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setUserId("test");
        entity.setTitle("Do test");
        //when
        List<TodoEntity> todos = todoService.createTodo(entity);
        List<TodoEntity> entities = todoRepository.findByUserId(entity.getUserId());
        //then
        assertThat(todos).containsAll(entities);
    }

    @Test
    void retrieve() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setUserId("test");
        entity.setTitle("Do test");
        //when
        List<TodoEntity> todo = todoService.retrieve(entity.getUserId());
        List<TodoEntity> entities = todoRepository.findByUserId(entity.getUserId());
        //then
        assertThat(todo).containsAll(entities);
    }

    @Test
    void update() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setUserId("test");
        entity.setTitle("Do test");
        todoService.createTodo(entity);
        //when
        entity.setTitle("Update Test");
        List<TodoEntity> update = todoService.update(entity);
        List<TodoEntity> retrieve = todoService.retrieve(entity.getUserId());
        //then
        assertThat(update).containsAll(retrieve);
    }

    @Test
    void delete() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setUserId("test");
        entity.setTitle("Do test");
        todoService.createTodo(entity);
        //when
        List<TodoEntity> delete = todoService.delete(entity);
        //then
        assertThat(delete.size()).isEqualTo(0);
    }
}