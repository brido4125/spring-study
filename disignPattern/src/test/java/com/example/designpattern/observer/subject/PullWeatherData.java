package com.example.designpattern.observer.subject;

import com.example.designpattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class PullWeatherData implements Subject{
    private final List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public PullWeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }


    /*
     * Subject가 가지고 있는 모든 Observer에게 상태를 전달!
     * */
    @Override
    public void notifyObservers() {
        for(Observer obs : observers) {

        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
