package com.example.designpattern.facade;

public class TheaterLights {
    public void on() {
        System.out.println("TheaterLights on");
    }

    public void off() {
        System.out.println("TheaterLights off");
    }

    public void dim(int i) {
        System.out.println("TheaterLights dim " + i);
    }
}
