package hellojpa.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
//@Entity
public class OrderItem {
    @Id @GeneratedValue @Column(name = "ORDER_ITEM_ID")
    private Long id;

    /*
    * OrderItem 이 '다' 쪽이니까 Many to One
    * */
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name ="ITEM_ID")
    private Item item;

    private int price;
    private int count;
}
