package com.example.designpattern.factory.pizza;

import com.example.designpattern.factory.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;

    Dough dough;
    Sauce sauce;
    Veggies[] veggies;
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    public abstract void prepare();

    public void bake() {
        System.out.println("Baking " + name);
    }

    public void cut() {
        System.out.println("Cutting " + name);
    }

    public void box() {
        System.out.println("Boxing " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
