package com.example.designpattern.facade;

public class StreamingPlayer {
    public void on() {
        System.out.println("StreamingPlayer on");
    }

    public void off() {
        System.out.println("StreamingPlayer off");
    }

    public void play(String movie) {
        System.out.println("StreamingPlayer playing " + movie);
    }

    public void stop() {
        System.out.println("StreamingPlayer stopped");
    }

    public void pause() {
        System.out.println("StreamingPlayer paused");
    }

    public void setSurroundAudio() {
        System.out.println("StreamingPlayer surround audio on (5 speakers, 1 subwoofer)");
    }

    public void setTwoChannelAudio() {
        System.out.println("StreamingPlayer two channel audio on");
    }
}
