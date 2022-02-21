package brido.me.todo.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data//Getter + Setter
public class TodoEntity {
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
