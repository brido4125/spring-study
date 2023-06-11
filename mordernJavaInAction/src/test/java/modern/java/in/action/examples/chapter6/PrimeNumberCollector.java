package modern.java.in.action.examples.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumberCollector implements Collector<
        Integer,
        Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>> {
    /*
    * supplier -> 누적자를 만드는 함수 반환
    * Map -> true인 ArrayList
    * Map -> false인 ArrayList 각각 put
    * */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new ConcurrentHashMap<>(){
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    /*
    * 스트림의 요소를 생성된 누적자에 추가해야 하는 함수
    * 소수인지 아닌지를 판별해야함
    * */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }
}
