package modern.java.in.action.examples.chapter5;

import modern.java.in.action.examples.chapter4.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest {

    List<Dish> menu = new ArrayList<>();
    List<Dish> specialMenu = new ArrayList<>();

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

        specialMenu.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        specialMenu.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        specialMenu.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        specialMenu.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        specialMenu.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
    }

    @Test
    void filterTest() {
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        vegetarianMenu.forEach((Dish dish) -> System.out.println(dish.getName()));
    }

    @Test
    void distinctTest() {
        List<Integer> numbers = List.of(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    void takeWhileTest() {
        /*
        * specialMenu is already sorted by calories
        * */

        List<Dish> byFilter = specialMenu.stream()
                .filter(d -> d.getCalories() < 320)
                .collect(Collectors.toList());

        List<Dish> byTakeWhile = specialMenu.stream()
                .takeWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());

        assertThat(byFilter).isEqualTo(byTakeWhile);
    }

    @Test
    void dropWhileTest() {
        List<Dish> byTakeWhile = specialMenu.stream()
                .takeWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());

        List<Dish> byDropWhile = specialMenu.stream()
                .dropWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());

        assertThat(byTakeWhile.size() + byDropWhile.size()).isEqualTo(specialMenu.size());
    }

    @Test
    void limitTest() {
        List<Dish> dishes = specialMenu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        assertThat(dishes.size()).isEqualTo(3);
    }

    @Test
    void skipTest() {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .collect(Collectors.toList());

        List<Dish> bySkip = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        assertThat(bySkip.size() + 2).isEqualTo(dishes.size());
    }
}
