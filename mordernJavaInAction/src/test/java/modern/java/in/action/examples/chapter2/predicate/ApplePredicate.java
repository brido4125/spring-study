package modern.java.in.action.examples.chapter2.predicate;

import modern.java.in.action.examples.chapter2.Apple;

public interface ApplePredicate<T> {
    boolean test(T apple);
}
