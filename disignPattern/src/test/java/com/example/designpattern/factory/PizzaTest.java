package com.example.designpattern.factory;

import com.example.designpattern.factory.pizzastore.ChicagoStylePizzaStore;
import com.example.designpattern.factory.pizzastore.NYPizzaStore;
import com.example.designpattern.factory.pizzastore.Pizza;
import com.example.designpattern.factory.pizzastore.PizzaStore;
import org.junit.jupiter.api.Test;

public class PizzaTest {
    @Test
    void ex1() {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoStylePizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
