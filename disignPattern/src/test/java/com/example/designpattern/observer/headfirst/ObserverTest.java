package com.example.designpattern.observer.headfirst;

import com.example.designpattern.observer.headfirst.subject.WeatherData;
import org.junit.jupiter.api.Test;

public class ObserverTest {
    @Test
    void test() {
        WeatherData subject = new WeatherData();

        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(subject);

        subject.setMeasurements(80, 65, 30.4f);
        subject.setMeasurements(82, 70, 29.2f);
        subject.setMeasurements(78, 90, 29.2f);
    }
}
