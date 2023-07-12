package com.example.designpattern.strategy.sample;

public class SalesOrderLine implements OrderLine {
    private Product product;
    private int quantity;

    public SalesOrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return product.getPrice() * quantity;
    }
}
