package sample.cafekiosk.spring.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTypeTest {
    @DisplayName("상품 타입이 재고 관련 타입인지를 채크한다.")
    @Test
    void containsStockType(){
        //given
        ProductType handmade = ProductType.HANDMADE;
        //when
        boolean result = ProductType.containsStockType(handmade);
        //then
        Assertions.assertThat(result).isEqualTo(false);
    }


}