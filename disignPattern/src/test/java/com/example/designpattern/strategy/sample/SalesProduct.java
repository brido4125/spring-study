package com.example.designpattern.strategy.sample;

public class SalesProduct implements Product {

    private final String id;
    private final String group;
    private final String description;
    private final long price;

    public SalesProduct(String id, String group, String description, long price) {
        this.id = id;
        this.group = group;
        this.description = description;
        this.price = price;
    }

    @Override
    public void operation() {
        System.out.println("SalesProduct: Performing operation");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public long getPrice() {
        return price;
    }
}
