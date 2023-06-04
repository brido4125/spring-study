package com.example.designpattern.factory.pizza;

import com.example.designpattern.factory.PizzaIngredientFactory;

public class PepperoniPizza extends Pizza {
    private final PizzaIngredientFactory ingredientFactory;

    public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("준비 중 : " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clams = ingredientFactory.createClams();
    }
}
