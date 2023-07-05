package sample.cafekiosk.unit.order;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.unit.beverage.Beverage;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class Order {

    private final LocalDateTime localDateTime;

    private final List<Beverage> beverages;

    public List<Beverage> getBeverages() {
        return beverages;
    }
}
