package com.example.designpattern.observer.sample2;

public class NormalSpeedClock extends Observer {

    private final Timer timer;

    public NormalSpeedClock(Timer timer) {
        this.timer = timer;
        timer.attach(this);
    }

    @Override
    public void update(Subject subject){
        sleep(1000);
        if (this.timer == subject){
            System.out.printf(
                "Normal Clock  : Updated/Synchronized to :  %02d:%02d:%02d %n",
                timer.getHour(), timer.getMinute(), timer.getSecond());
        }
    }
}
