package com.example.designpattern.strategy.sample;

public interface Order {
    long getPrice();

    long getNetPrice();

    long getNetPrice(PricingStrategy pricingStrategy);

    void createOrderLine(Product product, int quantity);
}
