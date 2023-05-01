package thread.creation.ex1;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            //Code that will run in a new thread
            System.out.println("We are now in thread: " + Thread.currentThread().getName());
            System.out.println("Current thread priority is: " + Thread.currentThread().getPriority());
            throw new RuntimeException("Intentional Exception");
        });
        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler((t,e) -> {
            //This code will run when an exception is thrown in the thread
            System.out.println("A critical error happened in thread: " + t.getName()
                    + " the error is: " + e.getMessage());
        });
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

    }

}
