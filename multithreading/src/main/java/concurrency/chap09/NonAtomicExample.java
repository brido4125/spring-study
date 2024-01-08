package concurrency.chap09;

public class NonAtomicExample {

    private static int value = 0;
    private static int NUM_THREAD = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREAD];
        for (int i = 0; i < NUM_THREAD; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    //value의 값을 1씩 증가
                    int expectedValue = value;
                    int newValue = expectedValue + 1;
                    value = newValue;
                    System.out.println(Thread.currentThread().getName() + ":" + expectedValue + ":" + newValue);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("value = " + value);
    }
}
