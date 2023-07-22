package com.example.designpattern.decorator.sample1;

import com.example.designpattern.decorator.sample1.component.Component;

public class Decorator2 extends Decorator {
    public Decorator2(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        String operation = super.operation();
        return addBehavior(operation);
    }

    private String addBehavior(String result) {
        return result + " + Decorator2";
    }
}
