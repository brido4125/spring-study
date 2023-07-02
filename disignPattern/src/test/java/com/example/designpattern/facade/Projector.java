package com.example.designpattern.facade;

public class Projector {

    StreamingPlayer streamingPlayer;

    public void on() {
        System.out.println("Projector on");
    }

    public void off() {
        System.out.println("Projector off");
    }

    public void tvMode() {
        System.out.println("Projector tvMode");
    }

    public void wideScreenMode() {
        System.out.println("Projector wideScreenMode");
    }

    public void setStreamingPlayer(StreamingPlayer streamingPlayer) {
        this.streamingPlayer = streamingPlayer;
        System.out.println("Projector setting StreamingPlayer to " + streamingPlayer);
    }
}
