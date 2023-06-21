package com.example.designpattern.command.v1.receiver;

public class Light {

    private String location;

    public Light() {
    }

    public Light(String location) {
        this.location = location;
    }

    public void on() { System.out.println("Light is on"); }
    public void off() { System.out.println("Light is off"); }
}
