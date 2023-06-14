package modern.java.in.action.examples.chapter6;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


/*
* T : 스트림 요소의 타입
* A : 누적자의 타입
* R : 수집 연산의 결과 타입
* */
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
    * 소수로만 나누는 방식
    * */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity(); // 항등 함수
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private static boolean isPrime(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    private static boolean upgradeIsPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
