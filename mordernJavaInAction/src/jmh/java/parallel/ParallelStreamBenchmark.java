package parallel;

import org.openjdk.jmh.annotations.*;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {
    private static final long N = 10_000_000L;

    //@Benchmark
    public long sequentialSum() {
        return java.util.stream.Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .reduce(0L, Long::sum);
    }


    //@Benchmark
    public long parallelSum() {
        return java.util.stream.Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .parallel()
                .reduce(0L, Long::sum);
    }


    @Benchmark
    public long longSequentialSum() {
        return java.util.stream.LongStream.rangeClosed(1, N)
                .reduce(0L, Long::sum);
    }


    @Benchmark
    public long longParallelSum() {
        return java.util.stream.LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }
}
