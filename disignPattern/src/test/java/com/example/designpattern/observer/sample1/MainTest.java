package com.example.designpattern.observer.sample1;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void main() {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer = new ConcreteObserver(subject);
        Observer observer2 = new ConcreteObserver2(subject);

        subject.setState(1);
        subject.setState(2);
    }
}
