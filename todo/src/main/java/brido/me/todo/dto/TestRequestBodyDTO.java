package brido.me.todo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TestRequestBodyDTO {
    private int id;
    private String message;
}
