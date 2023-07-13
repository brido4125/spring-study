package com.example.designpattern.templatemethod;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void test() {
        Coffee coffee = new Coffee();
        coffee.prepareRecipe();

        Tea tea = new Tea();
        tea.prepareRecipe();
    }
}
