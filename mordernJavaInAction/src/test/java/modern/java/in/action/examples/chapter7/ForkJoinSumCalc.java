package modern.java.in.action.examples.chapter7;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalc extends RecursiveTask<Long> {
    private final long[] numbers;
    /*
    * start,end -> 연산을 시작할 numbers 배열의 시작과 끝 인덱스
    * */
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
        /*
        * Task split to half chuck.
        * */
        ForkJoinSumCalc leftTask = new ForkJoinSumCalc(numbers, start, start + length / 2);
        //start the Task asynchronously, specifically push this job to queue.
        leftTask.fork();

        ForkJoinSumCalc rightTask = new ForkJoinSumCalc(numbers, start + length / 2, end);
        Long compute = rightTask.compute();//main thread will compute rightTask.
        Long join = leftTask.join();//get result of leftTask.
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
