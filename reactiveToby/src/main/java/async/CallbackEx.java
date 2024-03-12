package async;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallbackEx {

  interface SuccessCallback {
    void onSuccess(String result);
  }

  interface ExceptionCallback {
    void onError(Throwable t);
  }

  public static class CallbackFutureTask extends FutureTask<String> {
    private final SuccessCallback sc;
    private final ExceptionCallback ec;

    public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ExceptionCallback ec) {
      super(callable);
      this.sc = Objects.requireNonNull(sc);
      this.ec = Objects.requireNonNull(ec);
    }

    @Override
    protected void done() {
      try {
        sc.onSuccess(get());
      } catch (ExecutionException e) {
        ec.onError(e.getCause());
      } catch (InterruptedException e) {
        // 인터럽트 발생했다는 신호를 처리해 줘야함
        Thread.currentThread().interrupt();
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService es = Executors.newCachedThreadPool();

    CallbackFutureTask f = new CallbackFutureTask(() -> {
      Thread.sleep(2000);
      if (1 == 1) {
        throw new RuntimeException("Async Error");
      }
      return "Hello";
    }, System.out::println, t -> {
      System.out.println("Error = " + t.getMessage());
    });

    es.execute(f);
    es.shutdown();
  }
}

