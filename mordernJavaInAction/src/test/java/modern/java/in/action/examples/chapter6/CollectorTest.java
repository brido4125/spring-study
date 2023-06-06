package modern.java.in.action.examples.chapter6;

import modern.java.in.action.examples.chapter4.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectorTest {

    List<Dish> menu = new ArrayList<>();

    @BeforeEach
    void setMenu() {
        menu.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        menu.add(new Dish("beef", false, 700, Dish.Type.MEAT));
        menu.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        menu.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
        menu.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        menu.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        menu.add(new Dish("pizza", true, 550, Dish.Type.OTHER));
        menu.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        menu.add(new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    void counting() {
        Long count = menu.stream().collect(Collectors.counting());
    }

    Comparator<Dish> comparator1 = (o1, o2) -> Math.max(o1.getCalories(), o2.getCalories());
    Comparator<Dish> comparator2 = Comparator.comparingInt(Dish::getCalories);

    @Test
    void maxBy() {
        Dish dish1 = menu.stream()
                .collect(Collectors.maxBy(comparator1)).get();
        Dish dish2 = menu.stream()
                .collect(Collectors.maxBy(comparator2)).get();
        assertThat(dish1.getName()).isEqualTo(dish2.getName());

        Optional<Dish> highCaloricDish = menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

    }

    @Test
    void summing() {
        Integer sum1 = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        int sum2 = 0;
        for (Dish dish : menu) {
            sum2 += dish.getCalories();
        }
        Assertions.assertThat(sum1).isEqualTo(sum2);

        Integer sumByReducing = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (a, b) -> a + b));
    }

}
