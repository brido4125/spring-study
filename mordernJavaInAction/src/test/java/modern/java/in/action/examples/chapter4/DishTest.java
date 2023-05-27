package modern.java.in.action.examples.chapter4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DishTest {

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
    void java7() {
        List<Dish> lowCaloricDishes = new ArrayList<>();

        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }

        lowCaloricDishes.sort((Dish d1, Dish d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        List<String> lowCaloricDishesName = new ArrayList<>();

        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }

        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);
    }

    @Test
    void streamTest() {
        List<String> lowCaloricDishesName = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);
    }

    @Test
    void newTest() {
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);
    }

    @Test
    void newTest2() {
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(4)
                .collect(Collectors.toList());
        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);
    }
}
