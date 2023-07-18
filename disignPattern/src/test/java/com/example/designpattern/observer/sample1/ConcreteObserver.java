package com.example.designpattern.observer.sample1;

public class ConcreteObserver extends Observer {
    private int state;
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        state = subject.getState();
        System.out.println("ConcreteObserver: State changed to " + state);
    }
}
