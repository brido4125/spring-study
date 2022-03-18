package hello.itemservice.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private String name;
    private Integer price;
    private Integer quantity;
}
