package com.example.designpattern.strategy.sample;

import java.util.ArrayList;
import java.util.List;

public class SalesOrder implements Order {

    private List<OrderLine> orderLines = new ArrayList<OrderLine>();
    private PricingStrategy strategy;

    public SalesOrder(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public long getPrice() {
        long total = 0;
        for (OrderLine orderLine : orderLines) {
            total += orderLine.getPrice();
        }
        return total;
    }

    @Override
    public long getNetPrice() {
        return strategy.calculate(this);
    }

    @Override
    public long getNetPrice(PricingStrategy pricingStrategy) {
        return pricingStrategy.calculate(this);
    }

    @Override
    public void createOrderLine(Product product, int quantity) {
        orderLines.add(new SalesOrderLine(product, quantity));
    }
}
