package com.example.designpattern.facade;

public class Amplifier {
    public void on() {
        System.out.println("Amplifier on");
    }

    public void setStreamingPlayer(StreamingPlayer dvd) {
        System.out.println("Amplifier setting DVD player to " + dvd);
    }

    public void setSurroundSound() {
        System.out.println("Amplifier surround sound on (5 speakers, 1 subwoofer)");
    }

    public void setVolume(int volume) {
        System.out.println("Amplifier setting volume to " + volume);
    }

    public void off() {
        System.out.println("Amplifier off");
    }
}
