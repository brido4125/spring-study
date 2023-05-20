package modern.java.in.action.examples.chapter2.predicate;

import modern.java.in.action.examples.chapter2.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
