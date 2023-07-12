package com.example.designpattern.strategy.sample;

public interface OrderLine {
    Product getProduct();

    int getQuantity();

    long getPrice();
}
