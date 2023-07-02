package com.example.designpattern.adapter.duck;

public class DuckAdapter implements Turkey {
    private final Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void gobble() {
        this.duck.quack();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            if (i != 4) {
                continue;
            }
            this.duck.fly();
        }
    }
}
