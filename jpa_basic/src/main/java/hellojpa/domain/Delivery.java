package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
//@Entity
public class Delivery {
    @Id
    @GeneratedValue @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus deliveryStatus;
}
