package com.example.designpattern.decorator;

import com.example.designpattern.decorator.beverage.Beverage;

public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";//먼저 들어간 요소(음료)를 먼저 출력

    }
    public double cost() {
        return .20 + beverage.cost();
    }
}
