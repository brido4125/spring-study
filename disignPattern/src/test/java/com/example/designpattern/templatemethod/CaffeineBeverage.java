package com.example.designpattern.templatemethod;

public abstract class CaffeineBeverage {

    /*
    * template method declare with final to prevent subclasses from changing the algorithm.
    * */
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public abstract void brew();

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    public abstract void addCondiments();
}
