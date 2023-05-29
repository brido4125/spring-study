package modern.java.in.action.examples.chapter5;

import modern.java.in.action.examples.chapter4.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindTest {

    private List<Dish> menu = new ArrayList<>();

    @BeforeEach
    void setUp() {
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
    void anyMatchTest() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }

    @Test
    void allMatchTest() {
        if (menu.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("The menu is (somewhat) healthy!!");
        }
    }

    @Test
    void findAnyTest() {
        Optional<Dish> any = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        Assertions.assertThat(any.get()).isInstanceOf(Dish.class);
    }

    @Test
    void reduce() {
        Integer integer = menu.stream()
                .map(d -> 1)
                .reduce(Integer::sum)
                .get();

        Assertions.assertThat(integer).isEqualTo(menu.size());
    }
}
