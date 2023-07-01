package com.example.designpattern.adapter;

import org.junit.jupiter.api.Test;

public class TurkeyTest {

    @Test
    void turkeyTest() {
        Duck duck = new MallardDuck();
        Turkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("\nThe Duck says...");
        testDuck(duck);

        System.out.println("\nThe TurkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
