package com.example.designpattern.decorator.beverage;

import com.example.designpattern.decorator.beverage.Beverage;

public class DarkRoast extends Beverage {

        public DarkRoast() {
            description = "Dark Roast Coffee";
        }

        @Override
        public double cost() {
            return .99;
        }
}
