package hello.itemservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemDTO {
    private String name;
    private Integer price;
    private Integer quantity;
}
