package modern.java.in.action.examples.chapter7;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalc extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalc(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalc(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    @Override
    public Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalc leftTask = new ForkJoinSumCalc(numbers, start, start + length / 2);
        leftTask.fork();

        ForkJoinSumCalc rightTask = new ForkJoinSumCalc(numbers, start + length / 2, end);
        Long compute = rightTask.compute();
        Long join = leftTask.join();
        return compute + join;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];

        }
        return sum;
    }
}
