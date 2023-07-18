package com.example.designpattern.observer.sample1;

public class ConcreteSubject extends Subject {

    private int state = 0;

    public int getState() {
        return state;
    }

    void setState(int state) {
        this.state = state;
        System.out.println("ConcreteSubject: State changed to " + state);
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        super.attach(observer);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}
