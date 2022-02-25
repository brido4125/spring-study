package brido.me.todo.service;

import brido.me.todo.persistence.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoServiceTest {
    private final TodoRepository todoRepository;

    @Autowired
    TodoServiceTest(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Test
    void createTodo() {

    }
}