package com.example.designpattern.observer.sample2;

public class DoubleSpeedClock extends Observer {
    private final Timer timer;

    public DoubleSpeedClock(Timer timer) {
        this.timer = timer;
        timer.attach(this);
    }

    @Override
    public void update(Subject subject) {
        sleep(500);
        if (this.timer == subject){
            System.out.printf(
                "Double Clock  : Updated/Synchronized to :  %02d:%02d:%02d %n",
                timer.getHour(), timer.getMinute(), timer.getSecond());
        }
    }
}
