package hello.itemservice.domain.item;

import lombok.Data;
import lombok.experimental.Delegate;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private boolean open;//판매여부
    private List<String> regions; // 등록지역
    private ItemType itemType;
    private DeliveryCode deliveryCode;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
