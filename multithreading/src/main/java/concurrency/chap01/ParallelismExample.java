package concurrency.chap01;

import java.util.ArrayList;
import java.util.List;

public class ParallelismExample {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("cpuCores = " + cpuCores);

        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            datas.add(i);
        }

        long start = System.currentTimeMillis();

        int sum = datas.parallelStream().mapToInt(i -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return i * i;
        }).sum();

        long end = System.currentTimeMillis();
        System.out.println("(end- start) = " + (end- start));
    }
}
