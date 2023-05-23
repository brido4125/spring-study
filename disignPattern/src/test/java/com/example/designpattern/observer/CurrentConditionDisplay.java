package com.example.designpattern.observer;


import com.example.designpattern.observer.subject.Subject;
import com.example.designpattern.observer.subject.WeatherData;

/*
* 현재 기상 조건을 나타내는 DisplayElement 구현
* */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private final WeatherData weatherData;


    /*
    * Display 객체가 생성 될때 바로 Observer로 등록
    * */
    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current Condition : " + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display(); // 상태가 변경될 때마다 display()를 호출해서 화면에 출력
    }


    /*
    * Data를 Pull 방식으로 가져오기 위해 Subject의 getter를 이용
    * */
    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }
}

