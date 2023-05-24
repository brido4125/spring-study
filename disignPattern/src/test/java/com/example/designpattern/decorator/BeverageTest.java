package com.example.designpattern.decorator;

import com.example.designpattern.decorator.beverage.Beverage;
import com.example.designpattern.decorator.beverage.Espresso;
import org.junit.jupiter.api.Test;

public class BeverageTest {
    @Test
    void testSingleBeverage() {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }

    @Test
    void testBeverageWithCondiment() {
        Beverage beverage = new Espresso();
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }
}
