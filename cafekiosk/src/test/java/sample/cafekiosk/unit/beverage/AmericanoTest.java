package sample.cafekiosk.unit.beverage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @Test
    void getPrice() {
        Americano americano = new Americano();
        assertEquals(4000, americano.getPrice());
    }

    @Test
    void getName() {
        Americano americano = new Americano();
        assertThat(americano.getName()).isEqualTo("Americano");
    }
}