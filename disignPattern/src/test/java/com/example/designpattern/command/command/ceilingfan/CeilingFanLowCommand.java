package com.example.designpattern.command.command.ceilingfan;

import com.example.designpattern.command.receiver.CeilingFan;

public class CeilingFanLowCommand extends CeilingFanCommand {
    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        setPrevSpeed(); // remember previous state
        ceilingFan.low();
    }
}
