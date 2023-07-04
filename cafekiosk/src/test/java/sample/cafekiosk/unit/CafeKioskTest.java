package sample.cafekiosk.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;

public class CafeKioskTest {

    @Test
    void menuTest() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 총 가격 : " + cafeKiosk.getTotalPrice());
    }

    @Test
    void add() {
        CafeKiosk kiosk = new CafeKiosk();
        kiosk.add(new Americano());

        Assertions.assertThat(kiosk.getBeverages().size()).isEqualTo(1);
        Assertions.assertThat(kiosk.getTotalPrice()).isEqualTo(4000);
    }

    @Test
    void remove() {
        CafeKiosk kiosk = new CafeKiosk();
        Americano americano = new Americano();
        kiosk.add(americano);

        kiosk.remove(americano);

        Assertions.assertThat(kiosk.getBeverages().size()).isEqualTo(0);
    }
}
