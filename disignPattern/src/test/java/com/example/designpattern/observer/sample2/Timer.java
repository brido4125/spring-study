package com.example.designpattern.observer.sample2;

public class Timer extends Subject {

    private int hour = 0;
    private int minute = 0;
    private int second = 0;

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void tick(int hour, int minute, int second) {
        System.out.print("By Subject ");
        System.out.println("Timer : Time of day changed to " + hour + ":" + minute + ":" + second);
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        notifyObservers();
    }


}
