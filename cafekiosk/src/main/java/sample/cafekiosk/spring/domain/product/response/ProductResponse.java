package sample.cafekiosk.spring.domain.product.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingStatus sellingType;
    private String name;
    private int price;

    public static ProductResponse of(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getProductNumber(),
            product.getType(),
            product.getSellingStatus(),
            product.getName(),
            product.getPrice()
        );
    }
}
