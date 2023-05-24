package com.example.designpattern.decorator.beverage;

import com.example.designpattern.decorator.beverage.Beverage;

public class HouseBlend extends Beverage {

        public HouseBlend() {
            description = "House Blend Coffee";
        }

        @Override
        public double cost() {
            return .89;
        }
}
