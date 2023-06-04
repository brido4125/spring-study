package com.example.designpattern.factory.pizzastore;

import com.example.designpattern.factory.ChicagoPizzaIngredientFactory;
import com.example.designpattern.factory.NYPizzaIngredientFactory;
import com.example.designpattern.factory.PizzaIngredientFactory;
import com.example.designpattern.factory.pizza.CheesePizza;
import com.example.designpattern.factory.pizza.ClamPizza;
import com.example.designpattern.factory.pizza.Pizza;
import com.example.designpattern.factory.pizza.VeggiePizza;

public class ChicagoStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

        if (type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("시카고 스타일 치즈 피자");
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("시카고 스타일 야채 피자");
        } else if (type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("시카고 스타일 조개 피자");
        } else if (type.equals("pepperoni")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("시카고 스타일 페퍼로니 피자");
        } else {
            System.out.println("지원하지 않는 피자입니다.");
        }
        return pizza;
    }
}
