package modern.java.in.action.examples.chapter5;

import modern.java.in.action.examples.chapter4.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MapTest {

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
    void mapTest() {
        List<Integer> nameLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        nameLength.forEach(System.out::println);
    }

    @Test
    void nonFlatMapTest() {
        List<String> words = List.of("Hello", "World");
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        collect.forEach(strings -> {
            for (String string : strings) {
                System.out.println(string);
            }
        });
    }

    @Test
    void flatMapTest() {
        List<String> words = List.of("Hello", "World");
        List<String> collect = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
        assertThat(collect.stream().distinct().count()).isEqualTo(7);
    }

    @Test
    void ex2() {
        List<Integer> numbers1 = List.of(1, 2, 3);
        List<Integer> numbers2 = List.of(3, 4);

        List<Integer> result = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> i * j))
                .collect(Collectors.toList());

        assertThat(result).containsExactly(3, 4, 6, 8, 9, 12);
    }
}
