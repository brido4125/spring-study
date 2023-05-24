package com.example.designpattern.decorator;

import com.example.designpattern.decorator.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    public abstract String getDescription();
}
