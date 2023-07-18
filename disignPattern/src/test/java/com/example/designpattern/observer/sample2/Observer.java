package com.example.designpattern.observer.sample2;

public abstract class Observer {
    public abstract void update(Subject subject);

    public void sleep(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
