package modern.java.in.action.examples.chpater3;

import modern.java.in.action.examples.chapter2.Apple;
import modern.java.in.action.examples.chapter2.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.comparing;

public class FunctionalInterfaceTest {

    @Test
    void predicateTest() {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> result
                = filter(Arrays.asList("test", "", "hi"), nonEmptyStringPredicate);
        Assertions.assertThat(result).containsExactly("test", "hi");
    }

    private <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();

        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }

        return results;
    }

    @Test
    void consumerTest() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        });
    }

    private <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i: list) {
            c.accept(i);
        }
    }

    @Test
    void freeVariableTest() {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        r.run();
    }

    @Test
    void methodReferenceTest() {
        List<Apple> inventory = Arrays.asList(new  Apple(Color.GREEN,140),
                new Apple(Color.GREEN,120));
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(comparing(Apple::getWeight));
    }

    @Test
    void methodRefExampleTest() {
        // 1.
        ToIntFunction<String> stringToInt1 = (String s) -> Integer.parseInt(s);
        ToIntFunction<String> stringToInt2 = Integer::parseInt;

        // 2.
        BiPredicate<List<String>, String> contains1 = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains2 = List::contains;
    }

    @Test
    void constructorRefTest() {
        /*
        * get()을 호출할 경우 새로운 Apple 객체 생성
        * */
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();


        /*
        * 110을 인수로 받는 생성자를 선택하고
        * 해당 인수의 값을 가지는 새로운 Apple 객체 생성
        * */
        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(110);


        /*
        * 두 가지 인수를 받는 생성자를 선택하고
        * 해당 인수의 값을 가지는 새로운 Apple 객체 생성
        * */
        BiFunction<Color, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(Color.GREEN, 110);
    }
}
