package modern.java.in.action.examples.chapter6;

import modern.java.in.action.examples.chapter4.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    void groupingMenu() {
        Map<Dish.Type, List<Dish>> listMap = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        for (Map.Entry<Dish.Type, List<Dish>> entry : listMap.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            entry.getValue().stream().map(Dish::getName).forEach(System.out::println);
        }
    }

    private enum CaloricLevel{
        DIET, NORMAL, FAT
    }

    @Test
    void groupingByCaloricLevel() {
        Map<CaloricLevel, List<Dish>> listMap = menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));

        for (Map.Entry<CaloricLevel, List<Dish>> entry : listMap.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            entry.getValue().stream().map(Dish::getName).forEach(System.out::println);
        }
    }

    @Test
    void multipleGroupBy() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> listMap = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

        for (Map.Entry<Dish.Type, Map<CaloricLevel, List<Dish>>> entry : listMap.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            for (Map.Entry<CaloricLevel, List<Dish>> entry1 : entry.getValue().entrySet()) {
                System.out.println("entry1.getKey() = " + entry1.getKey());
                entry1.getValue().stream().map(Dish::getName).forEach(System.out::println);
            }
            System.out.println("==========================");
        }
    }
    @Test
    void groupingBy500Cal() {
        Map<Dish.Type, List<Dish>> map = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.filtering(
                                dish -> dish.getCalories() > 500,
                                Collectors.toList())));

        for (Map.Entry<Dish.Type, List<Dish>> entry : map.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            entry.getValue().stream().map(Dish::getName).forEach(System.out::println);
        }
    }

    @Test
    void reverseGroupBy() {
        Map<CaloricLevel, Map<Dish.Type, List<Dish>>> reverse = menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, Collectors.groupingBy(Dish::getType)));

        for (Map.Entry<CaloricLevel, Map<Dish.Type, List<Dish>>> entry : reverse.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            for (Map.Entry<Dish.Type, List<Dish>> entry1 : entry.getValue().entrySet()) {
                System.out.println("entry1.getKey() = " + entry1.getKey());
                entry1.getValue().stream().map(Dish::getName).forEach(System.out::println);
            }
            System.out.println("==========================");
        }
    }

    @Test
    void partitioning() {
        Map<Boolean, List<Dish>> vegi = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));

        vegi.get(true).stream().map(Dish::getName).forEach(System.out::println);
    }

    @Test
    void partitioningWithType() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegi = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));

        for (Map.Entry<Boolean, Map<Dish.Type, List<Dish>>> entry : vegi.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            for (Map.Entry<Dish.Type, List<Dish>> entry1 : entry.getValue().entrySet()) {
                System.out.println("entry1.getKey() = " + entry1.getKey());
                entry1.getValue().stream().map(Dish::getName).forEach(System.out::println);
            }
            System.out.println("==========================");
        }
    }

    @Test
    void partitioningWithMaxCalories() {
        Map<Boolean, Dish> max = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));

        for (Map.Entry<Boolean, Dish> entry : max.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue().getName());
        }
    }


    @Test
    void predicatePrimeNumberWithCollector() {
        Map<Boolean, Long> primeNum = IntStream.rangeClosed(2, 100)
                .boxed()
                .collect(Collectors.partitioningBy(this::isPrime, Collectors.counting()));

        for (Map.Entry<Boolean, Long> entry : primeNum.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }
    }

    /*
    * 소수 판별 할 대상을 제곱근 이하의 수로 제한하는 방법
    * */
    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    @Test
    void groupingByWithMapping() {
        Map<Dish.Type, List<String>> collect = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.mapping(
                                Dish::getName,
                                Collectors.toList()
                        )
                ));

        for (Map.Entry<Dish.Type, List<String>> entry : collect.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            entry.getValue().stream().forEach(System.out::println);
        }
    }

    @Test
    void primeNumberWithPrimeNumberCollector() {
        Map<Boolean, List<Integer>> primeNum = IntStream.rangeClosed(2, 100)
                .boxed()
                .collect(new PrimeNumberCollector());

        for (Map.Entry<Boolean, List<Integer>> entry : primeNum.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            entry.getValue().forEach(System.out::println);
        }
    }
}
