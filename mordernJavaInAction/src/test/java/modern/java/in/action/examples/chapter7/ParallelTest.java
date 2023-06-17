package modern.java.in.action.examples.chapter7;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class ParallelTest {
    @Test
    void noneParallel() {
        long sum1 = sequentialSum(10000000);
        long sum2 = parallelSum(10000000);

        Assertions.assertThat(sum1).isEqualTo(sum2);
    }


    private long sequentialSum(long n) {
        long start = System.currentTimeMillis();
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        System.out.println("sequentialSum: " + (System.currentTimeMillis() - start));
        return sum;
    }

    private long parallelSum(long n) {
        long start = System.currentTimeMillis();
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("parallelSum: " + (System.currentTimeMillis() - start));
        return sum;
    }

    private long beforeStream(long n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
