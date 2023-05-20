package modern.java.in.action.examples.chapter2;

import modern.java.in.action.examples.chapter2.predicate.ApplePredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GreenAppleTest {
    /*
    * 1. Filter green apples
    * */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    * 2. Parameterize the color
    * */
    public static List<Apple> filterAppleByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
     * 2. Parameterize the color
     * */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    * 3. Filter Possible Field
    * */
    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if((flag && color.equals(apple.getColor())) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    * 4. Filter with Predicate
    * */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
    * 5. anonymous class
    * */
//    @Test
//    void anonymousClass() {
//        List<Apple> inventory = new ArrayList<>();
//        filterApples(inventory, new ApplePredicate<>() {
//            @Override
//            public boolean test(Apple apple) {
//                return Color.RED.equals(apple.getColor());
//            }
//        }
//    }

    /*
    * 6. Lambda
    * */
    @Test
    void lambda() {
        List<Apple> inventory = new ArrayList<>();
        filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
    }


    /*
    * 7. Generic
    * */
    public static <T> List<T> filter(List<T> inventory, ApplePredicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for(T apple: inventory) {
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    void comparatorTest() {
        List<Apple> inventory = new ArrayList<>();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
    }

    @Test
    void callableTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(() -> "Hello");
    }
}
