package com.example.designpattern.strategy.sample;

public interface PricingStrategy {
    long calculate(Order order);
}
