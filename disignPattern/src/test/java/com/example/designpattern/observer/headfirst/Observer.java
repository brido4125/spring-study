package com.example.designpattern.observer.headfirst;

public interface Observer {
    public void update(float temp, float humidity, float pressure);

    public void update();
}
