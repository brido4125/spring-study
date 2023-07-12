package com.example.designpattern.strategy.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {

    @Test
    void main() {
        Order order = new SalesOrder(new PercentagePricingStrategy());

        SalesProduct product1A = new SalesProduct("1A", "01", "Product A", 100);
        SalesProduct product1B = new SalesProduct("1B", "01", "Product B", 300);

        order.createOrderLine(product1A, 1);
        order.createOrderLine(product1B, 2);

        assertThat(order.getPrice()).isEqualTo(700);
        assertThat(order.getNetPrice()).isEqualTo(630);

        long thresholdNetPrice = order.getNetPrice(new ThresholdPricingStrategy());
        assertThat(thresholdNetPrice).isEqualTo(560);
    }
}
