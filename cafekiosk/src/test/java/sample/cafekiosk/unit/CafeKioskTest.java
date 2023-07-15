package sample.cafekiosk.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CafeKioskTest {

    @Test
    void menuTest() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 총 가격 : " + cafeKiosk.getTotalPrice());
    }

    @DisplayName("음료 추가")
    @Test
    void add() {
        CafeKiosk kiosk = new CafeKiosk();
        kiosk.add(new Americano());

        assertThat(kiosk.getBeverages().size()).isEqualTo(1);
        assertThat(kiosk.getTotalPrice()).isEqualTo(4000);
    }

    @Test
    void addCountTest() {
        CafeKiosk kiosk = new CafeKiosk();
        kiosk.add(new Americano(), 4);

        assertThat(kiosk.getBeverages().size()).isEqualTo(4);
        assertThat(kiosk.getTotalPrice()).isEqualTo(16000);
    }

    @Test
    void addZoroCountTest() {
        CafeKiosk kiosk = new CafeKiosk();

        Assertions.assertThatThrownBy(() -> kiosk.add(new Americano(), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("count는 0보다 커야 합니다.");
    }

    @Test
    void remove() {
        CafeKiosk kiosk = new CafeKiosk();
        Americano americano = new Americano();
        kiosk.add(americano);

        kiosk.remove(americano);

        assertThat(kiosk.getBeverages().size ()).isEqualTo(0);
    }

    @Test
    void createOrder() {
        CafeKiosk kiosk = new CafeKiosk();
        Americano americano = new Americano();

        kiosk.add(americano);

        Order order = kiosk.createOrder(LocalDateTime.of(2023, 1, 17, 10, 0));

        assertThat(order.getBeverages().size()).isEqualTo(1);

    }

    @DisplayName("")
    @Test
    void test(){
        //given

        //when

        //then
    }


    @Test
    void calculateTotalPrice() {
        // given
        CafeKiosk kiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();
        kiosk.add(americano, 1);
        kiosk.add(latte, 1);

        // when
        int totalPrice = kiosk.getTotalPrice();

        // then
        assertThat(kiosk.getTotalPrice()).isEqualTo(8500);
    }
}
