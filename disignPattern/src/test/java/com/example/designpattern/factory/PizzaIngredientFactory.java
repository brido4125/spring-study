package com.example.designpattern.factory;

import com.example.designpattern.factory.ingredient.*;

public interface PizzaIngredientFactory {
    /*
    * 아래 메서드들은 팩토리 메서드
    * */
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClams();
}
