package com.example.designpattern.strategy.sample;

public class PercentagePricingStrategy implements PricingStrategy {
    @Override
    public long calculate(Order order) {
        int percentage = 10;

        long amount = order.getPrice();
        long rabat = amount * percentage / 100;

        return amount - rabat;
    }
}
