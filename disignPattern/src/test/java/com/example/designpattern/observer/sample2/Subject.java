package com.example.designpattern.observer.sample2;


import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    //Register an observer
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //Notification
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
