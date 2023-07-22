package com.example.designpattern.decorator.sample1;

import com.example.designpattern.decorator.sample1.component.Component;
import com.example.designpattern.decorator.sample1.component.Component1;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void mainTest() {
        Component component = new Component1();
        System.out.println("component.operation() = " + component.operation());
        component = new Decorator1(new Decorator2(component));
        System.out.println("component.operation() = " + component.operation());
    }
}
