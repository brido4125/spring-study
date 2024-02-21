package brido.example.async.result;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureImpl<T> implements Future<T> {

  private final T value;

  public FutureImpl(T value) {
    this.value = value;
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return false;
  }

  @Override
  public boolean isCancelled() {
    return false;
  }

  @Override
  public boolean isDone() {
    return false;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public T get(long timeout, TimeUnit unit) {
    return value;
  }
}
