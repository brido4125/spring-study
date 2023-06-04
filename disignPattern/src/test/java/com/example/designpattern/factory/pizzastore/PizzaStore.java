package com.example.designpattern.factory.pizzastore;

import com.example.designpattern.factory.pizza.Pizza;
import com.example.designpattern.factory.simplefactory.SimplePizzaFactory;

public abstract class PizzaStore {

    SimplePizzaFactory factory;

    public Pizza orderPizza(String type) {

        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
