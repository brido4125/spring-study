package com.example.designpattern.command;

public class Stereo {
    void on() {
        System.out.println("Stereo is on");
    }

    void off() {
        System.out.println("Stereo is off");
    }

    void setCD() {
        System.out.println("Stereo is set for CD input");
    }

    void setVolume(int volume) {
        System.out.println("Stereo volume set to " + volume);
    }
}
