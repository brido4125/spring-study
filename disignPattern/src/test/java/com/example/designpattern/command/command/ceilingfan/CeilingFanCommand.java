package com.example.designpattern.command.command.ceilingfan;

import com.example.designpattern.command.command.Command;
import com.example.designpattern.command.receiver.CeilingFan;

public abstract class CeilingFanCommand implements Command {
    protected final CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanCommand(CeilingFan ceilingFan) { this.ceilingFan = ceilingFan; }

    public void undo() {
        switch (prevSpeed) {
            case CeilingFan.HIGH:
                ceilingFan.high();
                break;
            case CeilingFan.MEDIUM:
                ceilingFan.medium();
                break;
            case CeilingFan.LOW:
                ceilingFan.low();
                break;
            case CeilingFan.OFF:
                ceilingFan.off();
                break;
        }
    }

    protected void setPrevSpeed() {
        prevSpeed = ceilingFan.getSpeed();
    }
}
