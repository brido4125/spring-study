package com.example.designpattern.observer.sample1;

public class ConcreteObserver2 extends Observer {
    private int state;
    private ConcreteSubject subject;

    public ConcreteObserver2(ConcreteSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        state = subject.getState();
        System.out.println("ConcreteObserver2: State changed to " + state);
    }
}
