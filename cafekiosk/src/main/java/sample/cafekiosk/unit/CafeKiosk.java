package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk  {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if (count <= 0) {
          throw new IllegalArgumentException("count는 0보다 커야 합니다.");
        }
        for (int i = 0; i < count; i++) {
          beverages.add(beverage);
        }

    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int getTotalPrice() {
        return beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder(LocalDateTime localDateTime) {
        LocalTime localTime = localDateTime.toLocalTime();
        if (localTime.isBefore(SHOP_OPEN_TIME) || localTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalStateException("현재 시간은 주문 가능한 시간이 아닙니다.");
        }
        return new Order(LocalDateTime.now(), beverages);
    }
}
