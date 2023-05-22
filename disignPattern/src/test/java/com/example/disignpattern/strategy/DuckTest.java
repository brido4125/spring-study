package com.example.disignpattern.strategy;

import org.junit.jupiter.api.Test;

public class DuckTest {
    @Test
    void duckTest() {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.setFlyBehavior(() -> System.out.println("I can fly with rocket!"));
        model.performFly();
    }
}
