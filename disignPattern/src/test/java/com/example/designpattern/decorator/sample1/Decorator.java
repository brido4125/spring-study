package com.example.designpattern.decorator.sample1;

import com.example.designpattern.decorator.sample1.component.Component;

public abstract class Decorator extends Component {
    private final Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        // Forwarding to the component
        return component.operation();
    }
}
