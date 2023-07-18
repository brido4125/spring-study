package com.example.designpattern.observer.sample2;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class MainTest {

    /*
    * Sequence of events:
    * 1. time tick (hour, minute, second)
    * 2. notifyObservers()
    * 3. update() Each observer
    * */

    @Test
    void mainTest() throws InterruptedException {
        Timer timer = new Timer();
        NormalSpeedClock observer = new NormalSpeedClock(timer);
        DoubleSpeedClock speedClock = new DoubleSpeedClock(timer);

        for (int i = 0; i < 60; i++) {
            LocalTime now = LocalTime.now();
            int hour = now.getHour();
            int minute = now.getMinute();
            int second = now.getSecond();
            timer.tick(hour, minute, second);
        }

    }
}
