package com.example.designpattern.command;

public class Light {

    private String room;

    public Light() {
    }

    public Light(String living_room) {
        room = living_room;
    }

    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}
